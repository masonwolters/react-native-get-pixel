package org.bvic23.rngetpixel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;

import java.io.InputStream;
import java.util.ArrayList;

class RNPixelColorModule extends ReactContextBaseJavaModule {
    private Context context;

    public RNPixelColorModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "RNPixelColor";
    }

    @ReactMethod
    public void getPixelRGBAofImage(String imageName, float x, float y, final Callback callback) {
        try {
/*
                InputStream ims = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(ims, null);
                mImage.setImageDrawable(d);
*/
            WritableArray result = new WritableNativeArray();
            result.pushInt(255);
            result.pushInt(0);
            result.pushInt(0);
            callback.invoke(null, result);
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getPixelRGBAPolarOfImage(String imageName, float angle, float radius, final Callback callback) {
        try {
            InputStream inputStream = context.getAssets().open("drawable/" + imageName +".png");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            int pixel = bitmap.getPixel(0, 0);

            int r = Color.red(pixel);
            int g = Color.blue(pixel);
            int b = Color.green(pixel);

            WritableArray result = new WritableNativeArray();
            result.pushInt(r);
            result.pushInt(g);
            result.pushInt(b);
            callback.invoke(null, result);
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void findAngleOfNearestColor(String imageName, float minAngle, float maxAngle, float radius, final ReadableArray targetColor, final Callback callback) {
        try {
/*
                InputStream ims = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(ims, null);
                mImage.setImageDrawable(d);
*/
            callback.invoke(null, maxAngle);
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }
}

/*
#include "RNGetPixel.h"
#import "React/RCTImageLoader.h"
#import "React/RCTBridge.h"
#import "UIImage+RGBAAtPixel.h"
#import <math.h>

@implementation RNPixelColor

@synthesize bridge = _bridge;

static CGFloat rotation = M_PI / 2;

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getPixelRGBAofImage:(NSString *)imageName
                  atX:(CGFloat)x
                  atY:(CGFloat)y
                  callback:(RCTResponseSenderBlock)callback) {
    UIImage *image = [UIImage imageNamed:imageName];
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }
    [self getPixelColorFromImage:image atX:x atY:y callback:callback];
}

RCT_EXPORT_METHOD(getPixelRGBAPolarOfImage:(NSString *)imageName
                  angle:(CGFloat)angle
                  radius:(CGFloat)radius
                  callback:(RCTResponseSenderBlock)callback) {
    UIImage *image = [UIImage imageNamed:imageName];
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }

    CGFloat width = image.size.width;
    CGFloat height = image.size.height;
    CGFloat rotatedAngle = angle + rotation;

    CGPoint center = CGPointMake(width * 0.5, height * 0.5);

    CGFloat x = center.x + radius * cosf(rotatedAngle);
    CGFloat y = center.y + radius * sinf(rotatedAngle);
    [self getPixelColorFromImage:image atX:x atY:y callback:callback];
}

RCT_EXPORT_METHOD(findAngleOfNearestColor:(NSString *)imageName
                  minAngle:(CGFloat)minAngle
                  maxAngle:(CGFloat)maxAngle
                  radius:(CGFloat)radius
                  targetColor:(NSArray*)targetColor
                  callback:(RCTResponseSenderBlock)callback) {
    UIImage *image = [UIImage imageNamed:imageName];
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }

    CGFloat width = image.size.width;
    CGFloat height = image.size.height;
    CGPoint center = CGPointMake(width * 0.5, height * 0.5);
    CGFloat angle = minAngle;

    CGFloat targetRed = [targetColor[0] floatValue];
    CGFloat targetGreen = [targetColor[1] floatValue];
    CGFloat targetBlue = [targetColor[2] floatValue];

    CGFloat minDistance = CGFLOAT_MAX;
    CGFloat resultAngle = CGFLOAT_MAX;

    while (angle <= maxAngle) {
        CGFloat rotatedAngle = angle + rotation;
        CGFloat x = center.x + radius * cosf(rotatedAngle);
        CGFloat y = center.y + radius * sinf(rotatedAngle);

        CGFloat red;
        CGFloat green;
        CGFloat blue;
        [image rgbaAtPixel:CGPointMake(x, y) red:&red green:&green blue:&blue];

        CGFloat distance = powf(targetRed - red, 2) + powf(targetGreen - green, 2) + powf(targetBlue - blue, 2);

        if (distance < minDistance) {
            minDistance = distance;
            resultAngle = angle;
        }

        angle += M_PI / 180;
    }
    callback(@[[NSNull null], @(resultAngle)]);
}

- (void)getPixelColorFromImage:(UIImage*)image atX:(CGFloat)x atY:(CGFloat)y callback:(RCTResponseSenderBlock)callback {
    CGPoint point = CGPointMake(x, y);
    CGFloat red;
    CGFloat green;
    CGFloat blue;
    BOOL success = [image rgbaAtPixel:point red:&red green:&green blue:&blue];
    if (success) {
        callback(@[[NSNull null], @[@(red), @(green), @(blue)]]);
    } else {
        callback(@[@"Could not create image from given path.", @""]);
    }
}


@end

 */