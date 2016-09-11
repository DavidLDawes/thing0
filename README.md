# thing

This application was generated using JHipster, you can find documentation and help at [https://jhipster.github.io](https://jhipster.github.io).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
2. Using Node Package Manager (NPM) add dependencies. If you want to revise things by adding entities with
   jhipster then you need the yo and generator stuff. I get the following installed:

    npm install -g yo gulp grunt bower angular karma mocha jhipster generator-karma generator-webapp generator-angular generator-jhipster

After installing Node, clone this repository and run the npm and bower install commands:

    npm install
    bower install

you should be able to run the following command to install development tools after changes (like
[Bower][] and [BrowserSync][]). You will only need to run this command when dependencies change in package.json.

    npm install

We use [Gulp][] as our build system. If you skipped the bower install -g step above, install the Gulp command-line tool globally now with:

    npm install -g gulp

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    gulp

Bower is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in `bower.json`. You can also run `bower update` and `bower install` to manage dependencies.
Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

## mysql dependency

jhipster generated this app to use mysql in production, so you need to have mysql available, and you need to adjust the src/main/resource/config/application-prod.yml file to use the correct user name and password/

## Optional maven dependency

If you have Maven installed then you can build and launch with mvn, if you want to. For example, the next section uses Maven to get things running fast/

## Simple fast startup

Use the Maven mvn command with no options and it will serve the app at localhost:8080 by default

    mvn

If it all works you'll see the character mapped JHIPSTER string and eventually a success message like this:
----------------------------------------------------------
        Application 'thing' is running! Access URLs:
        Local:          http://127.0.0.1:8080
        External:       http://10.0.75.1:8080
----------------------------------------------------------

Now you can open it with your browser at localhost:8080

## Building for production

To optimize the thing client for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify CSS and JavaScript files. It will also modify `index.html` so it references
these new files.

To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Testing

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript/` and can be run with:

    gulp test

UI end-to-end tests are powered by [Protractor][], which is built on top of WebDriverJS. They're located in `src/test/javascript/e2e`
and can be run by starting Spring Boot in one terminal (`./mvnw spring-boot:run`) and running the tests (`gulp itest`) in a second one.

## Continuous Integration

To setup this project in Jenkins, use the following configuration:

* Project name: `thing`
* Source Code Management
    * Git Repository: `git@github.com:xxxx/thing.git`
    * Branches to build: `*/master`
    * Additional Behaviours: `Wipe out repository & force clone`
* Build Triggers
    * Poll SCM / Schedule: `H/5 * * * *`
* Build
    * Invoke Maven / Tasks: `-Pprod clean package`
    * Execute Shell / Command:
        ````
        ./mvnw spring-boot:run &
        bootPid=$!
        sleep 30s
        gulp itest
        kill $bootPid
        ````
* Post-build Actions
    * Publish JUnit test result report / Test Report XMLs: `build/test-results/*.xml,build/reports/e2e/*.xml`

[JHipster]: https://jhipster.github.io/
[Node.js]: https://nodejs.org/
[Bower]: http://bower.io/
[Gulp]: http://gulpjs.com/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
