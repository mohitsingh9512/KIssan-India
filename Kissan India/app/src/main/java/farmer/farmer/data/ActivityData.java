package farmer.farmer.data;


public class ActivityData {
    private String mTitle;
    private Class mActivityClass;

    public ActivityData()
    {}

    public ActivityData(String mTitle, Class mActivityClass) {
        this.mTitle = mTitle;
        this.mActivityClass = mActivityClass;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Class getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class mActivityClass) {
        this.mActivityClass = mActivityClass;
    }
}
