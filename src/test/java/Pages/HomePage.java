package Pages;

import Base.CommonActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonActions {
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='io.plaidapp:id/story_title_background']")
    public MobileElement grid;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Plaid']")
    public MobileElement toolbarTitle;

    @AndroidFindBy(accessibility = "Search")
    public MobileElement searchButton;

    @AndroidFindBy(id = "io.plaidapp:id/menu_theme")
    public MobileElement themeButton;

    @AndroidFindBy(accessibility = "Filter")
    public MobileElement filterButton;

    @AndroidFindBy(accessibility = "More options")
    public MobileElement moreOptionsButton;

    @AndroidFindBy(id = "io.plaidapp:id/filters")
    public MobileElement filterMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Popular Designer News']")
    public MobileElement popularDesignerNews;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='“Material Design”']")
    public MobileElement materialDesign;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Hunt']")
    public MobileElement productHunt;

    @AndroidFindBy(id = "io.plaidapp:id/no_filters")
    public MobileElement noFiltersSelected;

    @AndroidFindBy(className = "android.widget.ListView")
    public MobileElement moreOptionsMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in to Designer News']")
    public MobileElement logInDesignerNews;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='About']")
    public MobileElement about;

    @AndroidFindBy(id = "android:id/aerr_close")
    public MobileElement alertCloseButton;

    public List<MobileElement> getList_filterMenu() {
        List<MobileElement> list = new ArrayList<MobileElement>();
        list.add(popularDesignerNews);
        list.add(materialDesign);
        list.add(productHunt);
        return list;
    }

    public List<MobileElement> getList_moreOptions() {
        List<MobileElement> list = new ArrayList<MobileElement>();
        list.add(logInDesignerNews);
        list.add(about);
        return list;
    }

    public List<MobileElement> getList_toolbarIcons() {
        List<MobileElement> list = new ArrayList<MobileElement>();
        list.add(searchButton);
        list.add(themeButton);
        list.add(filterButton);
        list.add(moreOptionsButton);
        return list;
    }

    public boolean isAppCrashed() {
        ApplicationState state = getAppState();
        if (state == ApplicationState.RUNNING_IN_FOREGROUND) {
            alertCloseButton.click();
            return true;
        } else if (state == ApplicationState.NOT_RUNNING) {
            return true;
        } else {
            return false;
        }
    }

}
