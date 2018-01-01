package co.molzol.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by hp on 26-12-2017.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    protected Set<NetworkChangeReceiverListener> listeners;
    protected Boolean connected;

    public NetworkChangeReceiver() {
        listeners = new HashSet<NetworkChangeReceiverListener>();
        connected = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getExtras() == null){
            return;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        if(ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        } else if(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)) {
            connected = false;
        }

        notifyStateToAll();
    }

    private void notifyStateToAll() {
        for(NetworkChangeReceiverListener listener : listeners)
            notifyState(listener);
    }

    private void notifyState(NetworkChangeReceiverListener listener) {
        if(connected == null || listener == null)
            return;

        if(connected == true)
            listener.networkAvailable();
        else
            listener.networkUnavailable();
    }

    public void addListener(NetworkChangeReceiverListener l) {
        listeners.add(l);
        notifyState(l);
    }

    public void removeListener(NetworkChangeReceiverListener l) {
        listeners.remove(l);
    }

    public interface NetworkChangeReceiverListener {
        public void networkAvailable();
        public void networkUnavailable();
    }
}
