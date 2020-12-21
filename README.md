# Term paper DBS 2020

## Setup

1. Open new terminal window
2. Run database
   > cd docker && docker-compose up
3. Open new terminal window
4. Run create script
   > sqlcmd -S 127.0.0.1,15789 -U sa -P UPPERCASE_lowercase_10 -i db/scripts/create.sql