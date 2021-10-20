# High-density QR Code Detection 
This app is based on [ML Kit's vision sample](https://github.com/googlesamples/mlkit/tree/master/android/vision-quickstart). It demonstrates how to use ML Kit's barcode scanning API and Dynamsoft Barcode Reader API to detect high-density QR codes from a static image.


<img src="https://www.dynamsoft.com/codepool/img/2021/10/high-density-qr-code-detection.jpg" width="220"/> 

## Getting Started
1. Download [Dynamsoft Barcode Reader SDK v8.8](https://www.dynamsoft.com/barcode-reader/downloads).
2. Extract the SDK package and copy the `DynamsoftBarcodeReaderAndroid.aar` file to the `libs` folder of the project.

    Alternatively, you can configure the `build.gradle` file as follows:
    
    ```gradle
    allprojects {
        repositories {
            google()
            jcenter()
            maven {
                url "https://download2.dynamsoft.com/maven/dbr/aar"
            }
        }
    }
    
    dependencies {
        implementation 'com.dynamsoft:dynamsoftbarcodereader:8.8.0@aar'
    }
    ```
3. Build and run the project in Android Studio.

## Public Image Dataset
[https://boofcv.org/notwiki/regression/fiducial/qrcodes_v3.zip](https://boofcv.org/notwiki/regression/fiducial/qrcodes_v3.zip)

## High-density QR Code Detection Performance
The following chart shows the performance of the high-density QR code detection based on the public image dataset `qrcodes_v3/qrcodes/detection/high_version`.

![high-density QR code detection performance](https://www.dynamsoft.com/codepool/img/2021/10/high-density-qr-detection-performance.jpg)


## Documentation

* [ML Kit](https://developers.google.com/ml-kit/vision/barcode-scanning)
* [Dynamsoft Barcode Reader](https://www.dynamsoft.com/barcode-reader/programming/android/api-reference/index.html?ver=latest)

## Blog
[How to Detect High Density QR Code on Android Devices](https://www.dynamsoft.com/codepool/high-density-qr-code-detection.html)

