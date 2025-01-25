<h1 align="center" id="title">SayOTP - JavaFX TOTP Generator - V1</h1>

<p id="description">SayOTP is a JavaFX-based application that generates Time-Based One-Time Passwords (TOTP) using the Google Authenticator library. This application provides a user-friendly graphical interface for generating TOTP codes making it easy to use for two-factor authentication (2FA) purposes.</p>

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   <strong>TOTP Generation:</strong> Generates Time-Based One-Time Passwords (TOTP) using the provided secret key.
*   <strong>Google Authenticator Compatibility:</strong> Uses the Google Authenticator library to ensure compatibility with most TOTP-based 2FA systems.
*   <strong>Copy to Clipboard:</strong> Easily copy the generated TOTP code to the clipboard with a single click.
<img src="imgs/Screenshot from 2025-01-25 15-57-10.png">
<h2>🛠️ Installation Steps:</h2>
<h3>Requirements: <strong>Java 17+</strong> and <strong>Maven</strong>.</h3>

```
git clone https://github.com/Sayouti1/sayOTP.git
```

```
cd sayOTP
```

```
mvn clean javafx:run
```

<h2><strong> - Use:</strong></h2>
<p>You can name the account in the "Account label", which should be unique. The "key phrase" is the key that will be used to generate the TOTP.</p>
<img src="imgs/Screenshot from 2025-01-25 14-21-00.png">
<h2>🔴 Notice:</h2>
<p>In SayOTP, I currently store information as<strong> [Account label:keyPhrase]</strong> in a file called <strong>data.sayOTP</strong> as an initial method. You can access your credentials in this file. This will be changed in the upcoming version.</p>
<h2>Contributing</h2>
<p>Contributions are welcome! Please feel free to submit a Pull Request.</p>
<a href="https://github.com/Sayouti1/sayOTP/pulls" class="button">Submit a Pull Request</a>
