#include "RNGetPixel.h"
#import "React/RCTImageLoader.h"
#import "React/RCTBridge.h"
#import "UIImage+RGBAAtPixel.h"

@implementation RNPixelColor

@synthesize bridge = _bridge;

static NSString* lastImageName = nil;
static UIImage* image = nil;

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getPixelRGBAofImage:(NSString *)imageName
                  atX:(CGFloat)x
                  atY:(CGFloat)y
                  callback:(RCTResponseSenderBlock)callback) {
    if (image == nil || ![lastImageName isEqualToString:imageName]) {
        image = [UIImage imageNamed:imageName];
    }
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }
    [self getPixelColorFromImage:image atX:x atY:y callback:callback];
}

RCT_EXPORT_METHOD(getPixelRGBAPolarOfImage:(NSString *)imageName
                  angle:(CGFloat)angle
                  radius:(CGFloat)radius
                  callback:(RCTResponseSenderBlock)callback) {
    if (image == nil || ![lastImageName isEqualToString:imageName]) {
        image = [UIImage imageNamed:imageName];
    }
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }

    CGFloat width = image.size.width;
    CGFloat height = image.size.height;
    CGFloat rotatedAngle = angle + M_PI / 2;
    
    CGPoint center = CGPointMake(width * 0.5, height * 0.5);
    
    CGFloat x = center.x + radius * cosf(rotatedAngle);
    CGFloat y = center.y + radius * sinf(rotatedAngle);
    [self getPixelColorFromImage:image atX:x atY:y callback:callback];
}

- (void)getPixelColorFromImage:(UIImage*)image atX:(CGFloat)x atY:(CGFloat)y callback:(RCTResponseSenderBlock)callback {
    CGPoint point = CGPointMake(x, y);
    CGFloat red;
    CGFloat green;
    CGFloat blue;
    BOOL success = [image rgbaAtPixel:point red:&red green:&green blue:&blue];
    if (success) {
        return callback(@[[NSNull null], @[@(red), @(green), @(blue)]]);
    }
}


@end
