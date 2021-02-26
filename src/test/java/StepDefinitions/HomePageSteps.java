package StepDefinitions;

import Base.Base;
import Pages.HomePage;
import Base.CommonActions;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class HomePageSteps extends Base {
    HomePage homePage = new HomePage();
    CommonActions commonActions = new CommonActions();

    @Given("user views the App's name as {string} on the toolbar")
    public void userViewsTheAppSNameAsOnTheToolbar(String expectedTitle) {
        String actualTitle = homePage.toolbarTitle.getText();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("user views the App with {string} Theme")
    public void userViewsTheAppWithTheme(String theme) throws Exception {
        String isChecked = homePage.themeButton.getAttribute("checked");
        switch (theme) {
            case "Dark":
                Assert.assertEquals("true", isChecked);
                break;
            case "Light":
                Assert.assertEquals("false", isChecked);
                break;
            default:
                throw new Exception("!!! " + theme + " is not a defined theme !!!");
        }
    }

    @Then("user views Filter Menu with default selected items")
    public void userViewsFilterMenuWithDefaultSelectedItems() {
        Assert.assertTrue(homePage.filterMenu.isDisplayed());
        Assert.assertEquals("true", homePage.popularDesignerNews.getAttribute("enabled"));
        Assert.assertEquals("true", homePage.materialDesign.getAttribute("enabled"));
        Assert.assertEquals("false", homePage.productHunt.getAttribute("enabled"));
    }

    @When("user unselects all of the filters")
    public void userUnselectsAllOfTheFilters() {
        homePage.filterButton.click();
        List<MobileElement> elementList = homePage.getList_filterMenu();
        for (MobileElement element : elementList) {
            if (element.getAttribute("enabled").equalsIgnoreCase("true")) {
                element.click();
            }
        }
    }

    @When("user selects Popular Designer News")
    public void userSelectsPopularDesignerNews() {
        homePage.popularDesignerNews.click();
    }

    @Then("user views More Options menu items")
    public void userViewsMoreOptionsMenuItems() {
        List<MobileElement> elementList = homePage.getList_moreOptions();
        for (MobileElement element : elementList) {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    @Then("related menu opens")
    public void relatedMenuOpens() {
        Assert.assertFalse("!!! This scenario is failed because application crashes for this functionality !!!", homePage.isAppCrashed());
    }

    @When("user clicks {string} on the toolbar")
    public void userClicksOnTheToolbar(String element) throws Exception {
        switch (element) {
            case "Search Button":
                homePage.searchButton.click();
                break;
            case "Theme Button":
                homePage.themeButton.click();
                Thread.sleep(1000);
                break;
            case "Filter Button":
                homePage.filterButton.click();
                break;
            case "More Options Button":
                homePage.moreOptionsButton.click();
                break;
            default:
                throw new Exception("!!! " + element + " element is not found !!!");
        }
    }

    @When("user selects {string} on the Filter Menu")
    public void userSelectsOnTheFilterMenu(String element) throws Exception {
        switch (element) {
            case "Popular Designer News":
                homePage.popularDesignerNews.click();
                break;
            case "Material Design":
                homePage.materialDesign.click();
                break;
            case "Product Hunt":
                homePage.productHunt.click();
                break;
            default:
                throw new Exception("!!! " + element + " element is not found !!!");
        }
    }

    @Then("user views {string} on the home screen")
    public void userViewsOnTheHomeScreen(String filterResult) throws Exception {
        boolean isDisplayed = false;
        switch (filterResult) {
            case "selected items":
                isDisplayed = homePage.grid.isDisplayed();
                break;
            case "No filters selected":
                isDisplayed = homePage.noFiltersSelected.isDisplayed();
                break;
            default:
                break;
        }
        Assert.assertTrue("!!! There is no " + filterResult + " as Filter Result !!!", isDisplayed);
    }

    @When("user clicks {string} on the More Options Menu")
    public void userClicksOnTheMoreOptionsMenu(String element) throws Exception {
        switch (element) {
            case "About":
                homePage.about.click();
                break;
            case "Log In Designer News":
                homePage.logInDesignerNews.click();
                break;
            default:
                throw new Exception("!!! " + element + " element is not found !!!");
        }
    }


    @And("user views all required icons on the toolbar")
    public void userViewsAllRequiredIconsOnTheToolbar() {
        List<MobileElement> elementList = homePage.getList_toolbarIcons();
        for (MobileElement element : elementList) {
            Assert.assertTrue(element.isDisplayed());
        }
    }

    @And("user closes filter menu")
    public void userClosesFilterMenu() {
        commonActions.androidBackButton();
    }


}
