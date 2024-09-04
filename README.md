# Banking System

## Overview
This project implements a simple banking system in Java. The system supports the management of three types of bank accounts: checking, savings, and certificate of deposit (CD). The project processes a list of commands (in the form of strings), and performs various banking operations such as account creation, deposits, withdrawals, transfers, and time progression. Invalid commands are stored and outputted for user review. There is no direct user input or output; instead, the system interacts via a public method that takes a list of commands as input and returns a list of strings as output.

**[Link to GITLAB repository](https://gitlab.com/smomohturay/st3387-sam-turay-jr-summer-2024-001-se-181)**

## Features
- **Account Types Supported**:
  - Checking accounts
  - Savings accounts
  - Certificate of deposit (CD) accounts
- **Operations**:
  - Account creation
  - Deposit and withdrawal (with specific rules for each account type)
  - Money transfers between checking and savings accounts
  - APR (Annual Percentage Rate) calculations for all account types, with CDs accruing interest more frequently
  - Time passage, applying monthly fees and interest accumulation
- **Input and Output**:
  - Commands are processed as case-insensitive strings.
  - Invalid commands are stored and output at the end.
  - Output includes the current state of all open accounts and the history of valid transactions.

## Project Structure

### Core Classes
1. **Account.java**: 
   - Base class for checking, savings, and CD accounts. Handles common attributes like account ID, balance, and APR.
  
2. **SavingsAccount.java**:
   - Inherits from `Account`. Implements additional rules for savings accounts, including interest calculations and deposit/withdrawal limits.

3. **CheckingAccount.java**:
   - Inherits from `Account`. Implements rules specific to checking accounts, including lower deposit/withdrawal limits and monthly interest calculations.
   
4. **CDAccount.java**:
   - Inherits from `Account`. Represents a CD account, where funds are locked for a fixed period. CD accounts accrue interest more frequently but have stricter withdrawal rules.

5. **Bank.java**:
   - Manages the creation of accounts, processing of deposits, withdrawals, transfers, and time passage. It stores and validates all commands.

### Unit Test Classes
1. **AccountTest.java**: 
   - Unit tests for the `Account` class to ensure proper functionality of shared behaviors.
   
2. **SavingsAccountTest.java**: 
   - Unit tests for the `SavingsAccount` class, validating deposit/withdrawal rules and interest calculation.
   
3. **CheckingAccountTest.java**: 
   - Unit tests for the `CheckingAccount` class, ensuring compliance with checking account rules.
   
4. **CDAccountTest.java**: 
   - Unit tests for the `CDAccount` class, focusing on interest accrual and withdrawal restrictions.

5. **BankTest.java**: 
   - Tests for the `Bank` class to verify correct handling of accounts, transactions, and time passage.

## Command Rules

### Account Creation
- **Syntax**: `create <account type> <id> <apr> [amount for CD]`
- **Account Types**: Checking, Savings, CD
- Checking and Savings accounts are created with a balance of $0.
- CDs must be created with an initial balance between $1,000 and $10,000.
  
### Deposits
- **Syntax**: `deposit <id> <amount>`
- Maximum deposit for savings: $2,500.
- Maximum deposit for checking: $1,000.
- CDs cannot accept deposits after creation.

### Withdrawals
- **Syntax**: `withdraw <id> <amount>`
- Savings: Max $1,000 per withdrawal, once per month.
- Checking: Max $400 per withdrawal, no limit on the number of withdrawals.
- CDs: No withdrawals allowed until 12 months have passed; full balance must be withdrawn at once.

### Transfers
- **Syntax**: `transfer <from id> <to id> <amount>`
- Transfers are allowed between checking and savings accounts only.
  
### Time Progression
- **Syntax**: `pass <months>`
- Time is passed in monthly increments, applying fees, closing zero-balance accounts, and accruing interest.

## Example Commands and Outputs
### Example Input:
```
create savings 12345678 0.6
deposit 12345678 700
deposit 12345678 5000 // Invalid
create checking 98765432 0.01
deposit 98765432 300
transfer 98765432 12345678 300
pass 1
create cd 23456789 1.2 2000
```
### Example Output:
```
Savings 12345678 1000.50 0.60
Deposit 12345678 700
Transfer 98765432 12345678 300
Cd 23456789 2000.00 1.20
Invalid command: deposit 12345678 5000
```

## How to Run
1. **Clone the Repository**:
   ```bash
   git clone [https://github.com/yourusername/banking-system.git](https://gitlab.com/smomohturay/st3387-sam-turay-jr-summer-2024-001-se-181)
   ```
   
2. **Open In IntelliJ**:

3. **Run the Tests**:


## Future Enhancements
- Implement transaction logging to persistent storage.
- Add a graphical user interface (GUI) for user interaction.
- Expand the system to support more account types and features.
