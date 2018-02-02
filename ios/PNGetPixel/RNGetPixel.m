#include "RNGetPixel.h"
#import "React/RCTImageLoader.h"
#import "React/RCTBridge.h"
#import "UIImage+RGBAAtPixel.h"

@implementation RNPixelColor

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getPixelRGBAofImage:(NSString *)imageName
                  atX:(CGFloat)x
                  atY:(CGFloat)y
                  callback:(RCTResponseSenderBlock)callback)
{
    // TODO: readme, add "you should include your images as xcassets"
    UIImage *image = [UIImage imageNamed:imageName];
    if (image == nil) {
        return callback(@[@"Could not create image from given path.", @""]);
    }
    
    CGFloat red;
    CGFloat green;
    CGFloat blue;

    CGPoint point = CGPointMake(x, y);
    BOOL success = [image rgbaAtPixel:point red:&red green:&green blue:&blue];
    if (success) {
        return callback(@[[NSNull null], @[@(red), @(green), @(blue)]]);
    }
    
}

@end
