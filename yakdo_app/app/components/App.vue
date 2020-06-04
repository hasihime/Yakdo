<template>
    <Page>
        <ActionBar title="藥도" android:flat="true"/>
        <TabView android:tabBackgroundColor="#53ba82"
                 android:tabTextColor="#c4ffdf"
                 android:selectedTabTextColor="#ffffff"
                 androidSelectedTabHighlightColor="#ffffff">
            <TabViewItem title="지도">
                <GridLayout columns="*" rows="*">
                    <WebView src="https://k02a4051.p.ssafy.io/" @loaded="onLoaded"/>
                </GridLayout>
            </TabViewItem>
            <TabViewItem title="나만의 약국">
                <GridLayout columns="*" rows="*">
                    <Label class="message" text="Tab 2 Content" col="0" row="0"/>
                </GridLayout>
            </TabViewItem>
        </TabView>
    </Page>
</template>

<script >
  export default {
    data() {
      return {
        msg: 'Hello World!'
      }
    },methods: {
    //when the webview finishes loading
    onLoaded(event) {
    //get the webview
        const webView = event.object
        //if there is android
        if (webView.android) {
            //enable dom storage in the webview on android
            webView.android.getSettings().setDomStorageEnabled(true)
            webView.android.getSettings().setJavaScriptEnabled(true)
            webView.android.getSettings().setGeolocationEnabled(true)
            webView.android.getSettings().setJavaScriptCanOpenWindowsAutomatically(true)
            webView.android.setWebChromeClient(new android.webkit.WebChromeClient())
            }
        }
    }
  }
</script>

<style scoped>
    ActionBar {
        background-color: #53ba82;
        color: #ffffff;
    }

    .message {
        vertical-align: center;
        text-align: center;
        font-size: 20;
        color: #333333;
    }
</style>
