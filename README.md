# org.deepamehta-Reviews Module for DeepaMehta 4

A module for users who want to interactively extend a DeepaMehta 4 _Topic_- resp. _Association Type_ of their choice about a `Score` field.

The plugin provides by now two generic service methods allowing to either `/review/upvote` or `/review/downvote` _any_ given Topic (provided by ID) which has the "org.deepamehta.review.score"-in its type definition (model).

# API: REST-Interface

* GET `/review/upvote/{topicId}`
* GET `/review/downvote/{topicId}`

# Install org.deepamehta-Reviews Module

Download the latest `eduzen-reviews`-Bundle from [here](http://download.deepamehta.de/nightly/).

Place the downloaded file `eduzen-reviews-0.X.jar` in the `bundles` folder of your deepamehta installation and restart DeepaMehta.

# GNU Public License

This sofware is released under the terms of the GNU General Public License in Version 3.0, 2007.

# Icon License

The thumbs icons in use by this plugin (Thumbs Up, Thumbs Down) are both licensed under [GPL v2](http://www.gnu.org/licenses/gpl-2.0.html) and were designed by [Hylke](http://www.bomahy.nl).

The hashtag icon is licensed under Creative Commons [Attribution 3.0 Unported](http://creativecommons.org/licenses/by/3.0/) and was designed by [Yusuke Kamiyamane](http://p.yusukekamiyamane.com/).

The smiley icons are licensed under Creative Commons [Attribution-Share Alike 3.0 Unported](http://creativecommons.org/licenses/by-sa/3.0/) and were designed by [The Working Group](http://blog.twg.ca).

# Changelog

0.2, May 28, 2013

- shipping now with a generic PluginService (RESTful + ApplicationService)

0.1, Feb 28, 2013

- initialization of this plugin.

Author, Malte Reißig
