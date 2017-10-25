# Java Google SignIn

### Backend classes to obtain user information Google by access token.

Go to https://console.developers.google.com to create Oauth 2.0 Client ID.
Inloggegevens > Inloggegevens maken > Client-ID OAuth
The redirect URI that the class needs have to be the same in the developers console.

To get user information the class needs an access token. The client side of your app 
can obtain this token from Google by a sign in of the user. 
Documentation for getting this access token: https://developers.google.com/identity/sign-in/web/server-side-flow

The method getUserFromGoogle returns an GoogleUser object with the following data:
- User ID
- Given name
- Family name
- Email

##### The jar is in the target folder.