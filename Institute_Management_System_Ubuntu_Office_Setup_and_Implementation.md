# Institute Management System --- Ubuntu Office Laptop Setup & Implementation Guide

**Purpose:** Retrieve the existing project from GitHub on an Ubuntu
office laptop, open it in Spring Tool Suite (STS), run the Spring Boot
backend and Angular frontend, connect MySQL and Elasticsearch, test the
existing APIs, and continue implementing features safely.

**Repositories** - Backend:
https://github.com/Sahilghanwat/institute-management-system-backend -
Frontend:
https://github.com/Sahilghanwat/institute-management-system-frontend

**Known project setup** - Backend: Java / Spring Boot, Maven, REST APIs,
MySQL, Elasticsearch, Springdoc Swagger UI. - Frontend: Angular,
Node.js, npm. - Backend default port: `8080` - Angular default port:
`4200` - Elasticsearch was previously run in Docker with host port
`9201` mapped to container port `9200`. Verify the current Docker setup
before relying on that port. - MySQL database name used previously:
`Institude_info`. - Existing student API: `/api/v1/students`. - Swagger
UI: `http://localhost:8080/swagger-ui/index.html`. - Important: the
repository is the source of truth for the latest code. Exact
configuration and feature status may have changed since these notes were
written. Verify files and test each layer instead of assuming every
optional feature is working.

------------------------------------------------------------------------

## 1. Before starting: use the office laptop safely

1.  Follow your company's policy for GitHub, Docker, databases, and
    personal projects. Do not upload company data or secrets.
2.  Open a terminal: press `Ctrl + Alt + T`.
3.  Use your own authorized GitHub login. If the repositories are
    private, GitHub authentication will be required.
4.  Never paste database passwords, access tokens, or private keys into
    source code, Git commits, screenshots, or chat.
5.  Keep the backend and frontend in a single working folder for
    convenience, but do not merge their Git repositories.

## 2. Check the tools already installed

Run these commands one at a time:

``` bash
git --version
java -version
javac -version
```

Check Node.js and npm:

``` bash
node --version
npm --version
```

Check Docker:

``` bash
docker --version
docker compose version
docker ps
```

If a command is missing, do not install software on a managed office
laptop without permission. Ask IT or follow your company's approved
software process.

### Java version

The backend project has previously targeted Java 17. A newer JDK can
sometimes run a Java 17-targeted project, but use the JDK version
approved by the project and your company. In STS, set the installed JDK
and project compiler level consistently with `pom.xml`. Do not change
the project's Java version just to make an IDE warning disappear.

## 3. Create a workspace and clone both repositories

In Terminal:

``` bash
mkdir -p ~/workspace/institute-management-system
cd ~/workspace/institute-management-system
```

Clone the backend:

``` bash
git clone https://github.com/Sahilghanwat/institute-management-system-backend.git
```

Clone the frontend:

``` bash
git clone https://github.com/Sahilghanwat/institute-management-system-frontend.git
```

If either repository is private, authenticate when Git asks. Do not put
a GitHub password or token directly into a clone URL.

Confirm the folders:

``` bash
ls
```

You should see:

``` text
institute-management-system-backend
institute-management-system-frontend
```

Check each repository:

``` bash
cd ~/workspace/institute-management-system/institute-management-system-backend
git status
git remote -v
git branch --show-current
```

Then:

``` bash
cd ~/workspace/institute-management-system/institute-management-system-frontend
git status
git remote -v
git branch --show-current
```

The branch will normally be `main`. A clean working tree is expected
immediately after cloning.

## 4. Open and run the backend in Spring Tool Suite (STS)

### 4.1 Import the project

1.  Start Spring Tool Suite.

2.  Select a workspace location you can write to, for example
    `~/workspace/sts-workspace`.

3.  Choose **File → Import**.

4.  Choose **Maven → Existing Maven Projects**. Depending on STS
    version, this may be under **Maven**.

5.  For the root directory, browse to:

    `~/workspace/institute-management-system/institute-management-system-backend`

6.  Select the project detected from `pom.xml`.

7.  Click **Finish**.

8.  Wait for Maven dependency downloads and background builds to
    complete.

If Maven dependencies do not refresh: right-click the project → **Maven
→ Update Project**. If the project shows a JRE/JDK error, open **Window
→ Preferences → Java → Installed JREs**, add/select an installed JDK,
then check **Project → Properties → Java Compiler**. Match the Java
release in `pom.xml`.

### 4.2 Find backend configuration

In STS, open `src/main/resources/application.properties` or
`application.yml`, whichever exists in the checked-out project.

Review these settings locally: - MySQL JDBC URL, database name,
username, and password. - Elasticsearch URL. - Server port. - Any
environment-variable placeholders.

Do not commit your office-specific credentials. If the project has a
safe example configuration, copy it to an ignored local configuration
file; otherwise ask before changing tracked configuration. Use your
company's approved database and credentials. Do not assume a database on
your personal laptop exists on the office laptop.

### 4.3 Ensure MySQL is available

Check whether the service/client is available:

``` bash
mysql --version
```

If the MySQL server is local and your account has permission, service
status can be checked with:

``` bash
sudo systemctl status mysql
```

Do not run `sudo` or change service configuration if you lack
authorization. If MySQL is provided by Docker or a shared development
server, follow the company's approved setup.

The configured database name previously used was `Institude_info`;
verify the actual database/schema and user available to you. If the
application fails with "unknown database," create/use the approved
development database and confirm credentials with the project owner. Do
not copy production data to a personal or office development environment
without permission.

### 4.4 Start Elasticsearch in Docker (only if needed)

First inspect existing containers:

``` bash
docker ps -a
```

Look for an Elasticsearch container. A previous local setup used
container name `es-learning9`, image `elasticsearch:9.2.8`, and port
mapping `9201:9200`. These values are historical, not guaranteed to be
present on the office laptop.

If a suitable container already exists, start it only if appropriate:

``` bash
docker start es-learning9
```

If Docker says that container does not exist, do not assume the command
succeeded. Check the repository's Docker configuration or the
Elasticsearch version required by the project first. If you are
authorized and need to create a local development container, use the
version and security configuration approved for the project. Do not
expose Elasticsearch publicly or disable security on a
network-accessible instance.

Check container logs:

``` bash
docker logs --tail 100 es-learning9
```

If the prior port mapping is in use, test the endpoint only after
confirming the container is running:

``` bash
curl http://localhost:9201
```

Elasticsearch may require authentication or HTTPS depending on its
configuration. A connection error or an authentication response means
you must inspect the actual setup, not blindly change the application
URL.

### 4.5 Run the backend

In STS: 1. Find the Spring Boot main class, previously named
`InstitudeManagmentSystemFsjApplication`. 2. Right-click the class. 3.
Select **Run As → Spring Boot App**. 4. Read the Console for startup
errors. 5. Wait for a successful "Started ..." message and verify the
port.

Alternative from Terminal, if Maven is installed or the project includes
the Maven wrapper:

``` bash
cd ~/workspace/institute-management-system/institute-management-system-backend
./mvnw spring-boot:run
```

If `./mvnw` is absent, use the installed Maven command:

``` bash
mvn spring-boot:run
```

If the wrapper is present but not executable, inspect it first, then you
may run `chmod +x mvnw` in your local clone. Do not run both launch
methods simultaneously; they may compete for port `8080`.

### 4.6 Test the backend

Open Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

Test the student list endpoint:

``` bash
curl -i http://localhost:8080/api/v1/students
```

Expected success: an HTTP `200` response and JSON data, if the database
is available and the endpoint is accessible. Five records were returned
in a previous local test, but your office database may contain a
different number.

If Swagger does not load: - Confirm the backend started successfully. -
Check the console for port conflicts or database errors. - Confirm the
springdoc dependency and Swagger path in the checked-out project. - If
security is enabled, the endpoint may require login.

Other known student endpoints in the existing controller: -
`GET /api/v1/students` --- list students - `POST /api/v1/students` ---
create a student - `GET /api/v1/students/{id}` --- retrieve one
student - `PUT /api/v1/students/{id}` --- update a student -
`DELETE /api/v1/students/{id}` --- delete a student

Use the exact request schema shown by Swagger. Do not test destructive
operations against shared or production data.

## 5. Run the Angular frontend

Open a second Terminal window. Keep the backend running in the first
window.

``` bash
cd ~/workspace/institute-management-system/institute-management-system-frontend
```

Confirm the folder contains `package.json`:

``` bash
ls
```

Install the dependencies defined by the repository:

``` bash
npm install
```

For repeatable installs, use `npm ci` only when a compatible
`package-lock.json` exists and you want to install exactly from that
lock file. If the lock file is missing or inconsistent, inspect the
repository before changing dependency versions.

Start Angular:

``` bash
npx ng serve
```

Open:

`http://localhost:4200`

Stop the development server with `Ctrl + C`.

### If Angular cannot reach the backend

1.  Confirm Spring Boot is running on port `8080`.
2.  Inspect the Angular service for the backend API base URL.
3.  Confirm the backend CORS configuration allows the Angular origin
    `http://localhost:4200`.
4.  If session authentication is present, ensure the Angular HTTP
    requests use credentials as intended and the backend CORS
    configuration allows credentials for the exact frontend origin.
5.  Check browser Developer Tools → **Console** and **Network**. Read
    the actual status and response.
6.  Do not "fix" CORS by allowing every origin with credentials. Keep
    origins restricted to approved development hosts.

## 6. Understand the current project flow

The intended high-level request flow is:

``` text
Angular UI (localhost:4200)
        |
        | HTTP / JSON
        v
Spring Boot REST API (localhost:8080)
        |
        +---- MySQL: primary/persistent student and related records
        |
        +---- Elasticsearch: search index/copy used for search
```

MySQL is the source of truth for application records. Elasticsearch is a
search index and should be treated as a derived copy. A successful
database write does not automatically prove the Elasticsearch index is
synchronized; verify the implementation and test search after
create/update/delete.

The project includes student and trainer relationships. The `Student`
entity has a trainer association, and the `Trainer` entity has a
collection of students. When changing JSON serialization, be careful to
avoid recursive serialization between the two sides of the relationship.

## 7. Feature implementation workflow --- do one small change at a time

Use this cycle for every feature. Do not edit many layers at once.

### Step A --- Understand the existing code

1.  Read the relevant controller.
2.  Follow the method into the service class (the existing student
    service has historically been named `Student_Sercice`; preserve the
    repository's actual spelling).
3.  Inspect the repository interface.
4.  Inspect the entity and its relationships.
5.  Inspect the Angular service, component TypeScript, and template that
    call the API.
6.  Search the project for existing related methods before adding a
    duplicate.

### Step B --- Write down the intended behavior

Before editing, write a small checklist: - What should the user be able
to do? - Which endpoint and HTTP method are involved? - What request
body and response are expected? - What validation and error cases are
required? - Does the change affect MySQL, Elasticsearch, or both? - How
will you test it?

### Step C --- Change the backend first

1.  Update the entity only if the data model really needs to change.
2.  Add/update repository methods for database access.
3.  Put business logic in the service layer.
4.  Expose the operation through the REST controller.
5.  Add validation and consistent error handling where appropriate.
6.  Add/update tests.
7.  Run tests before touching the frontend.

Run:

``` bash
./mvnw test
```

If there is no wrapper:

``` bash
mvn test
```

Do not proceed until build/test failures are understood. Do not delete
tests merely to make the build pass.

### Step D --- Update Elasticsearch if the feature involves search

1.  Check the existing Elasticsearch document/index model and
    repository/service.
2.  Determine when the index is updated after MySQL create, update, or
    delete.
3.  Test the search endpoint with real development data.
4.  Verify behavior when Elasticsearch is unavailable and when a student
    has been updated or deleted.
5.  Avoid treating the search index as the only persistent copy of the
    data.

Known historical search routes were `/api/student/search/name?name=...`
and `/api/student/search/course?course=...`. Verify the actual mappings
in the current controller and Swagger before using them; do not assume
these paths still exist or that all search fields are indexed.

### Step E --- Update Angular

1.  Add or update a method in the appropriate Angular service.
2.  Call the service from the component.
3.  Show loading, success, and error states.
4.  Update the template only after confirming the response shape.
5.  Check browser Network tools for the request URL, status, and
    response body.
6.  Avoid hardcoding secrets in Angular. Frontend configuration is
    visible to users.

### Step F --- Verify end to end

For each feature: 1. Run backend tests. 2. Run the backend. 3. Test the
API in Swagger or with `curl`. 4. Run Angular. 5. Test the same
operation in the browser. 6. Verify the expected MySQL data. 7. If
search is involved, verify Elasticsearch results as well. 8. Record the
changed files and a short explanation in your notes.

## 8. Authentication notes (verify before relying on it)

An authentication extension was discussed for this project, including
session login/logout, public registration with the `STUDENT` role, and
restricting student management to `ADMIN` or `TRAINER`. The exact code
and compatibility were not confirmed by a successful clean build in
these notes.

Therefore: 1. Inspect the current `SecurityConfig`, authentication
controller, user entity/repository, and Angular auth service in the
cloned repositories. 2. Run backend tests and start the backend. 3. Test
register/login/current-user/logout only if those endpoints exist in the
current code. 4. If login fails for older user records, check whether
stored passwords are plaintext or BCrypt hashes. Do not expose
passwords; use an approved migration/reset process. 5. Public
registration must not allow a user to choose `ADMIN`. 6. Disabling CSRF
is suitable only for a controlled local development setup with a
deliberate security design; do not assume it is production-safe. 7. Do
not use real personal data or shared production credentials for testing.

## 9. Common errors and what to check

  -----------------------------------------------------------------------
  Symptom                             First checks
  ----------------------------------- -----------------------------------
  `git clone` asks for authentication Confirm repository visibility,
  or fails                            GitHub access, network, and
                                      approved authentication method.

  STS says project JDK is undefined   Configure an installed JDK under
                                      Installed JREs and align compiler
                                      level with `pom.xml`.

  Maven dependency download/build     Read the first meaningful error,
  fails                               check network/proxy access and Java
                                      version, then update Maven project.

  Port 8080 already in use            Check whether another backend is
                                      running. Stop only your own process
                                      or use an approved alternate port.

  MySQL connection refused            Verify the database service/host,
                                      port, credentials, and approved
                                      network access.

  Unknown database `Institude_info`   Confirm the configured schema
                                      exists and that the account has
                                      access.

  Elasticsearch connection error      Inspect `docker ps -a`, container
                                      logs, actual port mapping,
                                      authentication, and the application
                                      URL.

  Angular `npm install` fails         Check Node/npm versions,
                                      network/proxy, lock file, and the
                                      exact error. Do not delete lock
                                      files as a first step.

  Browser CORS error                  Verify API origin, backend CORS
                                      config, credentials policy, and
                                      actual Network response.

  API returns 401/403                 Check whether authentication is
                                      enabled, the current user/role, and
                                      session cookies.

  Search returns no results           Check MySQL record, Elasticsearch
                                      indexing/sync, index name/mapping,
                                      and the exact endpoint.
  -----------------------------------------------------------------------

Always diagnose using the first relevant error rather than changing
multiple settings at once.

## 10. Save and retrieve your work with Git

Before changing files:

``` bash
git status
git pull --ff-only
```

Only run `git pull --ff-only` when your working tree is clean and you
intend to bring in remote changes. If Git reports conflicts or local
changes, stop and inspect before proceeding.

After testing your changes, review the diff:

``` bash
git status
git diff
```

Stage only files that belong in the change. Example:

``` bash
git add src/main/java
git status
git commit -m "Describe the backend change"
git push
```

For Angular, use the equivalent commands in the frontend repository and
stage only the files you intended to change. Avoid `git add .` until you
have reviewed the status and verified that no secrets, generated files,
logs, or local configuration are included.

If you create a documentation file, put it in the repository you choose,
review it, then commit and push it like any other file. Never commit
`.env`, local passwords, private keys, database dumps, or company data.

## 11. Quick start checklist for each office day

-   [ ] Confirm you are allowed to use the laptop and repositories for
    this work.
-   [ ] Open terminal and `cd` to
    `~/workspace/institute-management-system`.
-   [ ] Run `git status` in both repositories before pulling.
-   [ ] Pull only when the working tree is clean and there are no
    unresolved changes.
-   [ ] Check MySQL and Elasticsearch availability.
-   [ ] Start the backend in STS and wait for a successful startup
    message.
-   [ ] Test the API or Swagger.
-   [ ] Start Angular with `npx ng serve`.
-   [ ] Verify the browser UI and API requests.
-   [ ] Make one small change at a time and test it.
-   [ ] Commit and push only intended files after reviewing the diff.

## 12. What to capture when something fails

If you need help troubleshooting, collect: 1. The exact command you ran.
2. The first meaningful error and nearby lines from the terminal/STS
console. 3. Output of `java -version`, `node --version`, and
`npm --version` if relevant. 4. `git status` (remove private paths if
required by policy). 5. Browser Network status and response body for API
issues.

Before sharing logs, redact passwords, tokens, cookies, email addresses,
private IPs if required by policy, and personal/student data. Do not
share `.env` files or credential files.

------------------------------------------------------------------------

**Reminder:** These steps are intended for a local development setup.
Always follow company policy and use approved development credentials
and data. Confirm each service is running and test each feature rather
than assuming the whole system is operational just because the
repositories cloned successfully.
