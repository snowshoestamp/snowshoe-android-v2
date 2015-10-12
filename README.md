SnowShoe-Android-v2
===================

The [SnowShoe Stamp](http://www.snowshoestamp.com) is an authentication tool for smartphones.

<p align="center" >
  <img src="https://beta.snowshoestamp.com/static/api/img/stamp.gif" alt="SnowShoe" title="SnowShoe" width="160" height="284">
</p>

## Installation

SnowShoe-Android-v2 is available through [jCenter](https://bintray.com/bintray/jcenter). To install
it, add the following line to your `build.gradle` dependencies:

```groovy
compile 'com.mattluedke:snowshoelib:1.0.4'
```

## Usage

The core piece of this library is the `SnowShoeView`, a subclass of `View` that automatically detects stamps and handles the API query.

You can add one to your layout xml:

```xml
<com.mattluedke.snowshoelib.SnowShoeView
  ...
/>
```

Then, assign your key and secret from the [SnowShoe Dashboard](https://beta.snowshoestamp.com/applications/application/list/) to the view:

```java
snowShoeView.setAppKeyAndSecret("YOUR_APP_KEY", "YOUR_APP_SECRET");
```

Then, implement `OnStampListener`, which will be notified when a stamp request is made to the API and when a result comes back:

```java
public interface OnStampListener {
  void onStampRequestMade();
  void onStampResult(StampResult result);
}
```

Then assign the listener to the `SnowShoeView`:

```java
snowShoeView.setOnStampListener(listener);
```

## Author

Matt Luedke, mluedke2@gmail.com

Hardware and API managed by [SnowShoe](http://snowshoestamp.com/)

## License

SnowShoe-Android-v2 is available under the MIT license. See the LICENSE file for more info.
