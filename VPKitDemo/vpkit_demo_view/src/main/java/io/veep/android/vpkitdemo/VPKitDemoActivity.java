package io.veep.android.vpkitdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import io.veep.android.vpkit_library.CustomViews.VPKPreview;
import io.veep.android.vpkit_library.Util.VPKUtils;
import io.veep.android.vpkit_library.VPKitApplication;

public class VPKitDemoActivity extends AppCompatActivity {
    String mImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpkit_demo);
        this.initialiseVPKit();
        this.setupViews();

    }

    private void initialiseVPKit () {

          /*

     APP CREDENTIALS: required

     These app credentials are for testing purposes only.
     To obtain credentials unique to your app visit the VEEPIO developer portal
     https://developer.veep.io
     */

        String appId = "VEEPIO_by_url_test_app_id";
        String clientId = "1zArpBErovQ1MjVHvigJqXwE8qt47U2Yy5XzG3CP";
        String clientSecret = "VpLIvEetceUnHBEIf6fLUwLxELBh2QesZ6iLLiPHCesRLXfOLLJNcFfmp03wJfGaJquO3V8KqHjtvzlufuXfWWgcpWVw9wxfBJNYdZh96JHV5hk44dJbqiCqplrKcSml";
        VPKitApplication.setAppId(appId, clientId, clientSecret);

    }

    private void setupViews () {
        TextView versionView = (TextView) findViewById(R.id.version);
        versionView.setText(versionString());
        configurePreview1();
        configurePreview2();
    }

    private void configurePreview1 () {
        VPKPreview preview = (VPKPreview)findViewById(R.id.vpk_preview1);

        String imageUrl = "https://raw.githubusercontent.com/veepionyc/VPKitDemo/master/VPKitDemoObjC/VPKitDemo/Assets.xcassets/stock_photo.imageset/photo-1468818461933-b1d79f62434e.jpg";
        ViewGroup.LayoutParams params = preview.getLayoutParams();
        preview.setImageUrl(imageUrl);
        preview.mImageView.setImageDrawable(getResources().getDrawable(R.drawable.viewwithurl));
        preview.setLayoutParams(params);
    }

    private void configurePreview2 () {
        VPKPreview preview = (VPKPreview)findViewById(R.id.vpk_preview2);

        String veepId= "1787";  // "560" // check which is correct for sandbox/prod
        ViewGroup.LayoutParams layoutParams = preview.getLayoutParams();
        preview.setVeepId(veepId);
        preview.mImageView.setImageDrawable(getResources().getDrawable(R.drawable.view));
        preview.setLayoutParams(layoutParams);

    }



    private String versionString () {
        return "v: " + appVersion();
    }

    private String appVersion () {
        String appVersion = "not found";
        PackageManager pManager = getPackageManager();
        String pName = getPackageName();
        PackageInfo pInfo;
        try {
            pInfo = pManager.getPackageInfo(pName, 0);
            appVersion = pInfo.versionName + "-" + pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            VPKUtils.logMessage("couldn't get appVersion");
        }
        return appVersion;

    }

}
