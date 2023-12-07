import smtplib
import ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import sys

# print("Python script started executing.")

# Receive input from Java
bill = sys.argv[1]

print(bill)
#print(f"Received input from Java: {bill}")

# Email configuration
sender_email = "dd8434982@gmail.com"  # Replace with your email
sender_password = "cqss xdtc zbyr snez"  # Replace with your password
subject = "Order Confirmation - BudgetBobby"

recipient_email = bill

# Email content
message = MIMEMultipart()
message["From"] = sender_email
message["To"] = recipient_email
message["Subject"] = subject
#{bill[1]} {bill[2]}

body = f"Hello {bill},\nYou're all set! Your order from  will be on its way soon.\nUntil then,\nSave, Eat, Repeat! "
message.attach(MIMEText(body, "plain"))

# Send the email
context = ssl.create_default_context()
with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
    server.login(sender_email, sender_password)
    server.sendmail(sender_email, recipient_email, message.as_string())

print("Email sent successfully.")
