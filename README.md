# Ontario Tax Calculation App

The Ontario Tax Calculation App is an Android application that helps users calculate their income tax based on the combined federal and provincial tax brackets for Ontario. The app also incorporates RRSP (Registered Retirement Savings Plan) contributions, allowing users to see how these contributions affect their taxable income and tax owed. Additionally, a "what-if" analysis tool with a slider helps users visualize the impact of adjusting RRSP deduction limits on their tax.

## Features

- **Tax Calculation**: Calculates income tax based on Ontario’s tax brackets, taking into account both federal and provincial rates.
- **RRSP Deduction**: Deducts RRSP contributions from the income when calculating the tax and displays the RRSP contribution limit for the next year.
- **What-If Analysis with Slider**: Users can adjust the RRSP deduction limit using a slider (range: $0 to $27,230), allowing them to see real-time changes in their tax calculation.
- **Data Persistence with Shared Preferences**: Saves user data, so it remains available when the app restarts.
- **Refresh Button**: Reloads data stored in Shared Preferences, allowing users to reset the app to its last saved state.

## Technical Details

- **Tax Calculation Class**: Manages all tax calculations, including deductions for RRSP contributions and tax owed based on Ontario’s tax brackets.
- **User Interface**: A single activity that displays calculated tax, RRSP limits, and includes an interactive slider for adjusting RRSP limits.

### Resources

- **Tax Brackets and RRSP Limits**:
  - [Ontario Tax Brackets and Rates](https://www.taxtips.ca/taxrates/on.htm)
  - [RRSP Contribution Limits](https://www.canada.ca/en/revenue-agency/services/tax/registered-plans-administrators/pspa/mp-rrsp-dpsp-tfsa-limits-ympe.html)
