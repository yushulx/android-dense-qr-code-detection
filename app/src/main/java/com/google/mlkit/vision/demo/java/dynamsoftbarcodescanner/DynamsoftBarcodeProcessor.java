package com.google.mlkit.vision.demo.java.dynamsoftbarcodescanner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.BitmapMlImageBuilder;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.demo.GraphicOverlay;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import com.dynamsoft.dbr.*;
import com.google.mlkit.vision.demo.InferenceInfoGraphic;
import com.google.mlkit.vision.demo.java.VisionProcessorBase;

/** Barcode Detector Demo. */
public class DynamsoftBarcodeProcessor extends VisionProcessorBase<List<TextResult>> {

    private static final String TAG = "BarcodeProcessor";

    private BarcodeReader barcodeScanner;

    private HandlerThread handlerThread;
    private volatile boolean isBusy = false;

    public DynamsoftBarcodeProcessor(Context context) {
        super(context);
        try {
            barcodeScanner = new BarcodeReader("t0068NQAAADX2QmfruW5EonmMyujx6OqPy5uhext7hx+sbVbWXOAbDHXUWHKzsZ4mYk0nJZc4CO+P8dZ1rwtpcRFWIMzDNls=");
            PublicRuntimeSettings settings = barcodeScanner.getRuntimeSettings();
            settings.expectedBarcodesCount = 0;
            barcodeScanner.updateRuntimeSettings(settings);
        } catch (BarcodeReaderException e) {
            e.printStackTrace();
        }

        handlerThread = new HandlerThread("dbr");
        handlerThread.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected Task<List<TextResult>> detectInImage(InputImage image) {
        return null;
    }

    @Override
    public void processBitmap(Bitmap bitmap, final GraphicOverlay graphicOverlay) {
        try {
            long frameStartMs = SystemClock.elapsedRealtime();
            TextResult[] results = barcodeScanner.decodeBufferedImage(bitmap, "");
            long frameEndMs = SystemClock.elapsedRealtime();

            if (results != null) {
                for (int i = 0; i < results.length; ++i) {
                    TextResult barcode = results[i];
                    graphicOverlay.add(new DynamsoftBarcodeGraphic(graphicOverlay, barcode));
                }
                graphicOverlay.add(
                        new InferenceInfoGraphic(
                                graphicOverlay,
                                frameEndMs - frameStartMs,
                                frameEndMs - frameStartMs,
                                null));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BarcodeReaderException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSuccess(@NonNull List<TextResult> results, @NonNull GraphicOverlay graphicOverlay) {
        for (int i = 0; i < results.size(); ++i) {
            TextResult barcode = results.get(i);
            graphicOverlay.add(new DynamsoftBarcodeGraphic(graphicOverlay, barcode));
        }
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Barcode detection failed " + e);
    }
}
