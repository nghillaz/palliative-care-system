# CSE360 Team22 README
Doctor Portal Application
--------------------------
File List
--------------------------
1. MainClass.java
2. MainMenuPanel.java
3. LoginPanel.java
4. Database.java
5. EditPersonalDetails.java
6. ForgotPasswordPanel.java
7. CreateAccountPanel.java

Patient Portal Application
--------------------------
File List
--------------------------
1. MainClass.java
2. MainMenuPanel.java
3. LoginPanel.java
4. Database.java
5. EditPersonalDetails.java
6. ForgotPasswordPanel.java
7. CreateAccountPanel.java
8. SetDoctorPanel.java
9. EnterSymptomsPanel.java

--------------------------
How to Run the Softwares
--------------------------
Double-click on the .jar file and the application will open.

Patient Portal Application
--------------------------
1. Create an account by clicking on the "Create an Account" button.
2. Once an account has been created, a local patient.csv file will be created along with a your_email_id.csv file which will log your symptoms data. This local file is also stored in the Amazon S3 server. 
3. Login using the credentials you created.
4. You can click on the "Enter Symptoms/Pain Level" button to enter respective symptoms.
5. You can click on "Set Doctor" button to set your preferred doctor.
6. You can click on "Edit Personal Details" button to change your personal details. Note: You will have to enter the the email ID you used to create the account to ensure that your data is updated. 
7. You can logout form the application by clicking on the "Logout" button.

Doctor Portal Application
--------------------------
1. Create an account by clicking on "Create an Account" button.
2. Once an account has been created, a local doctors.csv file will be created which will log the doctor information. This local file is also stored in the Amazon S3 server. 
3. Login using the credentials you created.
4. Once logged in you will be able to see the list of patients that are assigned to you. You will also be able to view the patient's severity based on the threshold values that have been set. By default, the threshold values for each symptom has been set to 5. The threshold values can be changed in the Main Menu and respectively updated. 
5. You also have the ability to view the patient's previous history by clicking on the "View Patient History" button.
6. You can click on the "Update Data" button to refresh the patient data. 
7. You can logout form the application by clicking on the "Logout" button.
