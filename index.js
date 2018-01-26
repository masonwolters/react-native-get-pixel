import {
  NativeModules,
} from 'react-native';

function getPixelHex(path, options) {
  return new Promise((resolve, reject) => {
      NativeModules.RNPixelColor.getPixelHex(path, options, (err, color) => {
        if (err) {
          return reject(err);
        }

        resolve(color);
      });
    });
  }


export default getPixelHex 
