# Term paper DBS 2020

## Requirements
- [docker](https://www.docker.com/get-started)
- [sqlcmd](https://docs.microsoft.com/en-us/sql/linux/sql-server-linux-setup-tools?view=sql-server-ver15)
- JDBC Driver

## JDBC Driver installation in IntelliJ Idea

1. [Download the library](https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15)
0. Unpack the `zip` or `tar.gz` file
0. Move `mssql-jdbc-8.4.1.jre8.jar` to current project into `lib/` directory
0. In IntelliJ, go to `File` > `Project Structure` > `Modules` > `Dependencies` > `+` > `JAR or Directories` > Select `lib/mssql-jdbc-8.4.1.jre8.jar`

## Setup

1. Open new terminal window
0. Run database
   > cd docker && docker-compose up
0. Open new terminal window
0. Run create script
   > sqlcmd -S 127.0.0.1,15789 -U sa -P UPPERCASE_lowercase_10 -i db/scripts/create.sql
0. Run insert script
   > sqlcmd -S 127.0.0.1,15789 -U sa -P UPPERCASE_lowercase_10 -i db/scripts/insert.sql

## Cleaning database structure and data
1. Open docker app
0. Go to `Containers / Apps` > delete container `docker_sqlserver_tul_dbs_2020`
0. Go to `Images` > delete image `docker_sqlserver_tul_dbs_2020`
0. Open terminal window
0. Delete volumes with stored data
   > docker volume rm $(docker volume ls -q | grep docker_sql)

## Contribution

1. Pick one of the issues assigned to you.
0. Checkout master and pull current version
   > git checkout master && git pull --rebase
0. Create a new branch with name `<issue-number>-<short-issue-description>`.
   > e.g. `git checkout -b 1-add-readme`
0. Commit changes into the branch.
0. Push commits to github.
0. Create pull request with `<your-branch-name>` as a source and `master` as a target. Add link of the related issue into the comment of the pull request.
0. Assign the PR to yourself and set someone else as a reviewer.
0. Merge PR after reviewer accepts your changes.
