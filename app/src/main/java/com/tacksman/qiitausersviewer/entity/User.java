package com.tacksman.qiitausersviewer.entity;

import com.google.gson.annotations.SerializedName;

// Qiita User Entity
public class User {

    @SerializedName("description")
    private String description;
    private String facebookId;
    private int followeesCount;
    private int followersCount;
    private String githubLoginName;
    private String id;
    private int itemsCount;
    private String linkedinId;
    private String location;
    private String name;
    private String organization;
    private int permanentId;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    private String twitterScreenName;
    private String website_url;

    public User() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public int getFolloweesCount() {
        return followeesCount;
    }

    public void setFolloweesCount(int followeesCount) {
        this.followeesCount = followeesCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getGithubLoginName() {
        return githubLoginName;
    }

    public void setGithubLoginName(String githubLoginName) {
        this.githubLoginName = githubLoginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getPermanentId() {
        return permanentId;
    }

    public void setPermanentId(int permanentId) {
        this.permanentId = permanentId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getTwitterScreenName() {
        return twitterScreenName;
    }

    public void setTwitterScreenName(String twitterScreenName) {
        this.twitterScreenName = twitterScreenName;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public void setWebsite_url(String website_url) {
        this.website_url = website_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (followeesCount != user.followeesCount) return false;
        if (followersCount != user.followersCount) return false;
        if (itemsCount != user.itemsCount) return false;
        if (permanentId != user.permanentId) return false;
        if (description != null ? !description.equals(user.description) : user.description != null)
            return false;
        if (facebookId != null ? !facebookId.equals(user.facebookId) : user.facebookId != null)
            return false;
        if (githubLoginName != null ? !githubLoginName.equals(user.githubLoginName) : user.githubLoginName != null)
            return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (linkedinId != null ? !linkedinId.equals(user.linkedinId) : user.linkedinId != null)
            return false;
        if (location != null ? !location.equals(user.location) : user.location != null)
            return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (organization != null ? !organization.equals(user.organization) : user.organization != null)
            return false;
        if (profileImageUrl != null ? !profileImageUrl.equals(user.profileImageUrl) : user.profileImageUrl != null)
            return false;
        if (twitterScreenName != null ? !twitterScreenName.equals(user.twitterScreenName) : user.twitterScreenName != null)
            return false;
        return website_url != null ? website_url.equals(user.website_url) : user.website_url == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        result = 31 * result + followeesCount;
        result = 31 * result + followersCount;
        result = 31 * result + (githubLoginName != null ? githubLoginName.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + itemsCount;
        result = 31 * result + (linkedinId != null ? linkedinId.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + permanentId;
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        result = 31 * result + (twitterScreenName != null ? twitterScreenName.hashCode() : 0);
        result = 31 * result + (website_url != null ? website_url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "description='" + description + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", followeesCount=" + followeesCount +
                ", followersCount=" + followersCount +
                ", githubLoginName='" + githubLoginName + '\'' +
                ", id='" + id + '\'' +
                ", itemsCount=" + itemsCount +
                ", linkedinId='" + linkedinId + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", permanentId=" + permanentId +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", twitterScreenName='" + twitterScreenName + '\'' +
                ", website_url='" + website_url + '\'' +
                '}';
    }
}
