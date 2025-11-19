import google.generativeai as genai
import os
import PIL.Image # For image handling
import io

try:
    print("Please input a ")
    genai.configure(api_key= 'AIzaSyBSMCMfRL_WxN3-MouXNTSHsaMEUnkxl0s')
except KeyError:
    print("Error: API key error")
    exit()

print("--Available Gemini Model---")
for m in genai.list_models():
    if('generateContent' in m.supported_generation_methods):
            print(f" - {m.name}")

print("-" * 30)

print("try:")
model_text = genai.GenerativeModel('models/gemini-1.5-flash-latest')
prompt_text = "what is a master resume"
response_text = model_text.generate_content(prompt_text)
print(f"Prompt: {prompt_text}")
print(f"Response: \n{response_text.text}\n")

