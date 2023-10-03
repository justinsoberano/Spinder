# AuthController Documentation

`AuthController` helps to initiate the login process and fetch the access token.

## Attributes

- **redirectURI**: A URI that represents the callback URL where Spotify will redirect the user 
                   after they grant or deny permission.
- **spotifyAPI**: An instance of `SpotifyApi` which holds the configuration details required 
                  to connect to Spotify's Web API, such as the client ID, client secret, and 
                  redirect URI.

## Endpoints

### 1. `GET /register`

Initiates the Spotify login process.

#### Attributes

- **authorizationCodeUriRequest**: A request object to fetch the URI which will direct the user 
                                   to Spotify's login page.

#### Query Parameters

- **scope**: A comma-separated list of scopes. It specifies the types of access required. Do not 
             modify these values plz!
- **show_dialog**: A boolean parameter. If set to `true`, it will always show the login dialog, 
                   even if the user had previously granted permissions. To skip the login process 
                   (after the first time), use `false`.

#### Response

- Redirects the user to Spotify's login page.

### 2. `GET /register/api`

Handles the callback from Spotify after the user has granted or denied permission.

#### Query Parameters

- **code**: This is the access token returned by Spotify after the user grants permission. 
            It's used to fetch the access and refresh tokens.

#### Response

- If successful, returns the access token.
- In case of an error, the error message is printed to the console.

#### Attributes

- **authorizationCodeRequest**: A request object to fetch the access and refresh tokens using 
                                the `code` provided by Spotify.

### 3. `GET /login`

- Similar to `/register`, the only difference is it retrieves the access token from
  Spotify's servers. 

### 4. `GET /login/api`

- Similar to `/register/api`, a fallback function to check if their access key has changed
  on Spotify's servers. This is caused if they change their name, profile picture, add a new
  playlist, etc.

## Things to do

- Implement error handling more gracefully, rather than just printing to the console.
- Spotify API builder may need its own function to be called.