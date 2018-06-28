# HTTP Server

An HTTP Server implemented according to the [8th Light Cob Spec](https://github.com/8thlight/cob_spec), a suite of tests used to validate a web server to ensure it adheres to [HTTP specifications](https://tools.ietf.org/html/rfc7230).

## How to run the Server

###### 1. Clone this repo:
```
git clone git@github.com:Gabbendorf/HTTP-Server.git
cd HTTP-Server
gradle jar
```
###### 2. Clone Cob Spec repo:
```
git clone git@github.com:8thlight/cob_spec.git
cd cob_spec
mvn package
```
###### 3. Start the Server:
```
cd HTTP-Server
Start Server from command line using two arguments:
- -p 5000, which specifies the port to listen on.
- -d PUBLIC_DIR_PATH, which specifies Cob Spec public directory to serve files from.
```
For example:
```
java -jar build/libs/HTTPServer.jar -p 5000 -d /User/somebody/cob_spec/public
```
## How to run the Cob Spec test suite

- go into `cob_spec` folder
- Start the Fitnesse server `java -jar fitnesse.jar -p 9090`
- Open your browser and go to http://localhost:9090 (follow [Cob Spec](https://github.com/8thlight/cob_spec) `README` for further instructions).




