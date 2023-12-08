import smtplib
import ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import sys

# print("Python script started executing.")

# Receive input from Java
customer_email = sys.argv[1]  # "areebafatima5294@gmail.com"
username = sys.argv[2]  # User's name passed from Java
restaurant = sys.argv[3]  # Restaurant name passed from Java
bill_amount = sys.argv[4]  # Bill amount passed from Java

print(customer_email)
#print(f"Received input from Java: {bill}")

# Email configuration
sender_email = "dd8434982@gmail.com"  # Replace with your email
sender_password = "cqss xdtc zbyr snez"  # Replace with your password
subject = "Order Confirmation - BudgetBobby"



# Email content
message = MIMEMultipart()
message["From"] = sender_email
message["To"] = customer_email
message["Subject"] = subject
#{bill[1]} {bill[2]}

body = f"Hello {username},\n\nYou're all set! Your order from {restaurant} will be on its way soon.\n Please keep Rs.{bill_amount} ready!\n\nUntil then,\nSave, Eat, Repeat! "
message.attach(MIMEText(body, "plain"))

# Send the email
context = ssl.create_default_context()
with smtplib.SMTP_SSL("smtp.gmail.com", 465, context=context) as server:
    server.login(sender_email, sender_password)
    server.sendmail(sender_email, customer_email, message.as_string())

print("Email sent successfully.")
