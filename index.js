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
