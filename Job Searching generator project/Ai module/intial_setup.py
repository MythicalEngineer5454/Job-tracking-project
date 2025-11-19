import google.generativeai as genai
import os
# import httpx # Usually not needed as a direct import with google-genai
import pathlib
import time # Needed for time.sleep
import json # Needed if you're going to parse JSON response

def main():
    api_key = None
    try:
        print("Please input your Gemini API key:")
        input_key = input().strip() # Always strip whitespace from user input
        
        if not input_key:
            print("Error: API key cannot be empty. Exiting.")
            exit()

        # Configure the Generative AI library with the API key
        genai.configure(api_key=input_key)
        api_key = input_key # Store for potential re-use or logging if needed

        print("API key configured successfully. Attempting to verify connection...")
        
        # A quick test call to verify the API key and connection
        # This will raise an exception if the key is invalid or network issues exist.
        try:
            # You don't need a client object for simple model access after configure
            # If you want to check connectivity, a simple model listing or a tiny generation is good.
            # Example: Fetching a model to ensure the key works
            _ = genai.GenerativeModel("gemini-1.5-flash-latest")
            print("Successfully connected to Gemini API.")
        except Exception as e:
            print(f"Error: Failed to connect to Gemini API with the provided key. Details: {e}")
            print("Please ensure your API key is correct and has access to the Generative Language API.")
            exit()

    except Exception as e: # Catch any errors during API key input or initial configuration
        print(f"An unexpected error occurred during API key setup: {e}")
        exit()

    print("Please input the full path to your PDF file (e.g., C:\\Users\\YourName\\document.pdf):")
    pdf_path_str = input().strip() # Strip whitespace from path input
    pdf_path = pathlib.Path(pdf_path_str)

    if not pdf_path.is_file():
        print(f"Error: File not found at {pdf_path}. Please check the path and try again.")
        exit()
    if pdf_path.suffix.lower() != '.pdf':
        print(f"Error: The provided file {pdf_path.name} is not a PDF. Please provide a PDF file.")
        exit()

    uploaded_pdf = None
    try:
        print(f"Uploading PDF from: {pdf_path}")
        # Use genai.upload_file directly
        uploaded_pdf = genai.upload_file(path=str(pdf_path), mime_type="application/pdf")
        print(f"Uploaded PDF. URI: {uploaded_pdf.uri}, Name: {uploaded_pdf.name}, State: {uploaded_pdf.state.name}") # Access state.name

        # Wait for the file to become ACTIVE
        while uploaded_pdf.state.name == "PROCESSING":
            print("PDF is still processing on Google's servers, waiting (5s)...")
            time.sleep(5)
            uploaded_pdf = genai.get_file(name=uploaded_pdf.name) # Use genai.get_file

        if uploaded_pdf.state.name == "FAILED":
            print(f"PDF upload and processing failed: {uploaded_pdf.state.name}. Please check the file or try again later.")
            exit()
        elif uploaded_pdf.state.name == "ACTIVE":
            print("PDF processing complete and ready for use.")
        else:
            print(f"Unexpected PDF state after processing: {uploaded_pdf.state.name}. Exiting.")
            exit()

    except Exception as e:
        print(f"Error during PDF upload or processing: {e}")
        exit()

    model_name = "gemini-1.5-flash-latest"
    prompt = """
    This is a resume. Extract all useful information from it.
    Organize the extracted data into a structured JSON object with descriptive keys.
    Your response MUST be a valid JSON object, and only the JSON object. Do NOT include any other text, preambles, or explanations.
    Include sections like:
    - Candidate Name
    - Contact Information (email, phone, LinkedIn, etc.)
    - Summary or Objective
    - Work Experience (list of jobs with title, company, dates, and key responsibilities/achievements)
    - Education (list of degrees/certifications, institutions, graduation dates)
    - Skills (list of technical and soft skills)
    - Awards, Projects, Publications, or other relevant sections if present.
    If a section is not found, include an empty list or null for its value. 
    the section can be added or deleted depends on availbility of data
    Ensure the JSON is perfectly valid and ready for parsing.
    """
    
    # Get the generative model instance
    model = genai.GenerativeModel(model_name)

    extracted_info = None
    try:
        print("Sending prompt to Gemini model...")
        response = model.generate_content( 
            contents=[prompt, uploaded_pdf],
            generation_config={"response_mime_type": "application/json"}
        )
        
        # Check if response has text content
        if response and hasattr(response, 'text') and response.text:
            print("\nGemini Response (raw):")
            print(response.text)
            try:
                extracted_info = json.loads(response.text)
                url = "http://localhost:8080/api/resume/upload-parsed"
                response = requests.post(url, json=extracted_info)

                if response.status_code == 200:
                    print("✅ Resume uploaded successfully.")
                else:
                    print(f"❌ Upload failed. Status: {response.status_code}, Message: {response.text}")


                #print("\nSuccessfully parsed JSON data:")
                #print(json.dumps(extracted_info, indent=2))

                #output_directory = pdf_path.parent 
                #output_filename = f"{pdf_path.stem}_extracted.json"
                #output_full_path = output_directory / output_filename

                #with open(output_filename, 'w', encoding='utf-8') as f:
                #    json.dump(extracted_info, f, indent=2, ensure_ascii=False)
                #print(f"Extracted data saved to {output_full_path}")

            except json.JSONDecodeError as e:
                print(f"Error: Gemini response was not valid JSON. {e}")
                print("Response content:")
                print(response.text)
        else:
            print("Gemini response was empty or did not contain text.")
            # For debugging, you might want to print the full response object
            # print(f"Full response object: {response}")

    except Exception as e:
        print(f"An error occurred during Gemini content generation: {e}")
        # You might want to inspect response.prompt_feedback or response.candidates[0].finish_reason
        # for more details on why generation might have failed or been blocked.

    finally:
        # Optional: Delete the uploaded file from Gemini File API if you don't need it for 48 hours
        if uploaded_pdf and uploaded_pdf.name: # Ensure uploaded_pdf was successfully created and has a name
            try:
                genai.delete_file(name=uploaded_pdf.name) # Use genai.delete_file
                print(f"Deleted file {uploaded_pdf.name} from Gemini File API.")
            except Exception as e:
                print(f"Error deleting file {uploaded_pdf.name}: {e}")
    with open("Resume_extracted.json", "r") as f:
    resume = json.load(f)

    response = requests.post("http://localhost:8080/api/resume/upload-parsed", json=resume)
    print(response.status_code, response.text)


if __name__ == "__main__":
    main()