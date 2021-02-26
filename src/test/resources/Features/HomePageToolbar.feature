Feature: Plaid App - Home Screen Toolbar Tests

  Scenario: Check if toolbar elements are displayed
    Given user views the App with "Light" Theme
    And user views the App's name as "Plaid" on the toolbar
    And user views all required icons on the toolbar

  Scenario: App theme is switched around
    Given user views the App with "Light" Theme
    When user clicks "Theme Button" on the toolbar
    Then user views the App with "Dark" Theme
    When user clicks "Theme Button" on the toolbar
    Then user views the App with "Light" Theme

  Scenario: Check default filters on the toolbar
    When user clicks "Filter Button" on the toolbar
    Then user views Filter Menu with default selected items

  Scenario: None of the filters are selected
    When user unselects all of the filters
    And user closes filter menu
    Then user views "No filters selected" on the home screen


  Scenario: More Options menu displays
    When user clicks "More Options Button" on the toolbar
    Then user views More Options menu items

  Scenario: Check if Search Button works as expected
    When user clicks "Search Button" on the toolbar
    Then related menu opens

  Scenario: Check if Log In Designer News option item works as expected
    Given user clicks "More Options Button" on the toolbar
    When user clicks "Log In Designer News" on the More Options Menu
    Then related menu opens

  Scenario: Check if About option item works as expected
    Given user clicks "More Options Button" on the toolbar
    When user clicks "About" on the More Options Menu
    Then related menu opens


  Scenario Outline: Filter selection - only one filter
    Given user unselects all of the filters
    When user selects "<filterValue>" on the Filter Menu
    And user closes filter menu
    Then user views "<filterResult>" on the home screen

    Examples:
      | filterValue           | filterResult        |
      | Popular Designer News | selected items      |
      | Material Design       | No filters selected |
      | Product Hunt          | No filters selected |


  Scenario Outline: Filter selection - 2 of 3 filters
    Given user unselects all of the filters
    When user selects "<filterValue1>" on the Filter Menu
    And user selects "<filterValue2>" on the Filter Menu
    And user closes filter menu
    Then user views "<filterResult>" on the home screen

    Examples:
      | filterValue1          | filterValue2    | filterResult        |
      | Popular Designer News | Material Design | selected items      |
      | Popular Designer News | Product Hunt    | selected items      |
      | Material Design       | Product Hunt    | No filters selected |
