# gator-android

Android App that shows you a feed of links extracted from your twitter account.

<hr>

### Technical Overview:
The android app provides a specialized client for viewing a list of links extracted from the user's twitter feed. The twitter feed is monitored via the twitter user steaming api from a remote server. The tweets are filtered for links and then the links are aggregated in a database along with the associated tweets. Links are crawled and relevant information is scraped, (e.g. web page title, domain, hero image, excerpt). The server provides a simple restful api for obtaining a json list of "link bundles" which contain all the information about the link as well as and array of the tweets associated with it.

The android client makes regular calls against the api server requesting updated links since a particular date. The link bundles are obtained and stored in a local database. The app tracks two additional variables of state for the link bundle; whether the link has been bookmarked and read.

### User Stories:
* User can access the app by providing their twitter credentials.
* Links are extracted and from their twitter feed and is displayed as an array list.
* Links are default ordered as newest first.
* Array list items contain a bookmark toggle, the title of the link web page, the domain, the number of tweets associated with this link, the number of hours since the link was first tweeted, and an icon link to the detail view.
* Webpage title is a link that opens the webpage in the preferred browser.
* A book mark icon can be selected to isolate links of interest.
* The detail view contains all the information of the list view and also displays the tweets associated with the link. V2 has the detail view containing a hero image from the web page and/or some excerpt or extracted content.
* The list view has action bar icons of search and an overflow icon containing: Refresh,  Mark All As Read, and a toggle for Show All Links/Show Updates Only.
* The detail View has action bar icons for: up back, Bookmark and a Mark As Read toggle.   
* Search on main page can be used to search across the webpage titles and domains. It also contains some special suggestions for Bookmarks!, Oldest First! and Most Tweeted which sort and filter for those conditions.

