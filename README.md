
# DeepaMehta 4 Reviews Module

A DeepaMehta 4 module for users and developers who want to  extend a DeepaMehta 4 _Topic_- resp. _Association Type_ of their choice about a `Score`, a `Good!` or a `Soso`-field.

The plugin provides four **type-independent service methods** allowing to _upvote_, _downvote_ or _+1 on good_ or _+1 on so-so_ on 
_any_ given topic (provided by ID). Regarding which ones you prefer more, just add the corrspending child-topics to the _Topic Type_ definition of your choice and you can start to use the service.

## API: REST-Interface

 * GET `/review/upvote/{topicId}` - writes +1 `org.deepamehta.review.score`
 * GET `/review/downvote/{topicId}` - writes -1 on `org.deepamehta.review.score`
 * GET `/review/soso/{topicId}` - writes +1 on `org.deepamehta.review.soso`
 * GET `/review/good/{topicId}` - writes +1 on `org.deepamehta.review.good`
 
Note: This model is not compatible with the dm4-webclient. Simply because the DM Webclient tries sends _String_ values back to the server despite we actually defined a `dm4.core.number`-Data Type in our model. Make sure your data gets no invariance and therefore instantiate/migrate all your topics of the given type to have stored simple _Integer_ default values (e.g. 0) here.

## Download & Installation

Download the latest `dm43-eduzen-reviews`-Bundle from [here](http://download.deepamehta.de/nightly/).

Place the downloaded file in the `bundles` folder of your deepamehta installation and restart DeepaMehta.

## GNU Public License

This sofware is released under the terms of the GNU General Public License in Version 3.0, 2007.

## Icon License

The thumbs icons in use by this plugin (Thumbs Up, Thumbs Down) are both licensed under [GPL v2](http://www.gnu.org/licenses/gpl-2.0.html) and were designed by [Hylke](http://www.bomahy.nl).

The hashtag icon is licensed under Creative Commons [Attribution 3.0 Unported](http://creativecommons.org/licenses/by/3.0/) and was designed by [Yusuke Kamiyamane](http://p.yusukekamiyamane.com/).

The smiley icons are licensed under Creative Commons [Attribution-Share Alike 3.0 Unported](http://creativecommons.org/licenses/by-sa/3.0/) and were designed by [The Working Group](http://blog.twg.ca).

## Version History

**0.3.8, Apr 19, 2016

- Compatible with DeepaMehta 4.8

**0.3.7, Dec 06, 2014

- Compatible with DeepaMehta 4.4
- Bugifix: worked around data invariance for topics instantiated via dm4-webclient

**0.3.6**, Nov 17, 2014

- Compatible with DeepaMehta 4.3

**0.3.5**, Apr 4, 2014

- Introduced "So So" and "Good" child-types to just count the degree of appreciation by users towards topics.

**0.3.4**, Feb 28, 2014

- Compatible with DeepaMehta 4.2

**0.2**, May 28, 2013

- shipping now with a generic PluginService (RESTful + ApplicationService)

**0.1**, Feb 28, 2013

- initialization of this plugin.

-------------------------------
Author, Malte Reißig, 2013-2014

