# Term paper DBS 2020

## Requirements
- [docker](https://www.docker.com/get-started)
- [sqlcmd](https://docs.microsoft.com/en-us/sql/linux/sql-server-linux-setup-tools?view=sql-server-ver15)

## Setup

1. Open new terminal window
2. Run database
   > cd docker && docker-compose up
3. Open new terminal window
4. Run create script
   > sqlcmd -S 127.0.0.1,15789 -U sa -P UPPERCASE_lowercase_10 -i db/scripts/create.sql
5. Run insert script
   > sqlcmd -S 127.0.0.1,15789 -U sa -P UPPERCASE_lowercase_10 -i db/scripts/insert.sql

## Contribution

1. Pick one of the issues assigned to you.
2. Create a new branch on current master with name `<issue-number>-<short-issue-description>`.
> e.g. `git checkout -b 1-add-readme`
3. Commit changes into the branch.
4. Push commits to github.
5. Create pull request with `<your-branch-name>` as a source and `master` as a target. Add link of the related issue into the comment of the pull request.
6. Assign the PR to yourself and set someone else as a reviewer.
7. Merge PR after reviewer accepts your changes.
