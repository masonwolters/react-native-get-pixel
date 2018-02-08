import {
  NativeModules,
} from 'react-native';

export function getPixelRGBA(imageName, x, y) {
  return new Promise((resolve, reject) => {
      NativeModules.RNPixelColor.getPixelRGBAofImage(imageName, x, y, (err, color) => {        
        if (err) {
          return reject(err);
        }
        resolve(color);
      });
  });
}

export function getPixelRGBAPolar(imageName, angle, radius) {
  return new Promise((resolve, reject) => {
      NativeModules.RNPixelColor.getPixelRGBAPolarOfImage(imageName, angle, radius, (err, color) => {        
        if (err) {
          return reject(err);
        }
        console.log("-----<", color)
        resolve(color);
      });
  });
}

export function findAngleOfNearestColor(imageName, minAngle, maxAngle, radius, targetColor) {
  return new Promise((resolve, reject) => {
      NativeModules.RNPixelColor.findAngleOfNearestColor(imageName, minAngle, maxAngle, radius, targetColor, (err, angle) => {        
        if (err) {
          return reject(err);
        }
        resolve(angle);
      });
  });
}