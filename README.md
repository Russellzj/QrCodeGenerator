# QrCodeGenerator Overview

QrCoddeGenerator creates a simple local web service using REST architecture, allowing users to interact with URLs and generate qrcodes based on their input.

# Features

## RESTful API Endpoints:
- /api/health: Provides the health status of the service.
- /api/qrcode: Generates a QR code based on URL parameters.

## Dynamic URL Generation:
- The /api/qrcode endpoint takes user-specified variables from the URL to generate a QR code for a URL of their choice.
- Default variables are provided for QR code generation if no parameters are specified.

## Error Handling:
- The service will display informative error messages if the user submits unacceptable or invalid variables.
