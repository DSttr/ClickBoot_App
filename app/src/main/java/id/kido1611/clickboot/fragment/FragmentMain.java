package id.kido1611.clickboot.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;

import eu.chainfire.libsuperuser.Shell;
import id.kido1611.clickboot.ClickListener;
import id.kido1611.clickboot.MainActivity;
import id.kido1611.clickboot.R;
import id.kido1611.clickboot.RecyclerTouchListener;
import id.kido1611.clickboot.data.ClickAdapter;
import id.kido1611.clickboot.data.ClickItem;

/**
 * Created by Kido1611 on 05-May-16.
 */
public class FragmentMain extends Fragment {

    public static final String SHUTDOWN_BROADCAST
            = "am broadcast android.intent.action.ACTION_SHUTDOWN";
    public static final int RUNNABLE_DELAY_MS = 1000;
    public static final int ANIMATION_DURATION = 500;
    public static final String REBOOT_RECOVERY_CMD = "reboot recovery";
    public static final String SHUTDOWN = "reboot -p";
    public static final String REBOOT_CMD = "reboot";
    public static final String REBOOT_SOFT_REBOOT_CMD = "setprop ctl.restart zygote";
    private static final String REBOOT_BOOTLOADER_CMD = "reboot bootloader";
    private static final String[] REBOOT_SAFE_MODE = new String[] {
            "setprop persist.sys.safemode 1",
            REBOOT_SOFT_REBOOT_CMD
    };
    public static final int BG_PRIO = android.os.Process.THREAD_PRIORITY_BACKGROUND;

    public FragmentMain(){

    }

    private RecyclerView mClickList;
    private ClickAdapter mAdapter;
    private ArrayList<ClickItem> mItems = new ArrayList<ClickItem>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new CheckRoot().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mClickList = (RecyclerView) rootView.findViewById(R.id.list);
        mAdapter = new ClickAdapter(getActivity(), mItems);

        mClickList.setAdapter(mAdapter);
        mClickList.setHasFixedSize(true);
        mClickList.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.count_grid)));

        mClickList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mClickList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final ClickItem item = mItems.get(position);
                final NiftyDialogBuilder builder = NiftyDialogBuilder.getInstance(getActivity());

                final CardView card = (CardView) view.findViewById(R.id.cardview);
                card.setCardBackgroundColor(getResources().getColor(R.color.colorAccent));
                final TextView textTitle = (TextView) view.findViewById(R.id.title);
                textTitle.setTextColor(Color.WHITE);

                builder
                    .withTitle(getString(item.getTitleRes()))
                    .withMessage(getString(R.string.confirm))
                    .withDividerColor("#11000000")
                    .withDialogColor(getResources().getColor(R.color.colorAccent))
                    .isCancelableOnTouchOutside(false)
                    .withDuration(ANIMATION_DURATION)
                    .withEffect(item.getEffect())
                    .withButton2Text(getString(android.R.string.cancel))
                    .withButton1Text(getString(android.R.string.ok))
                    .setButton1Click(new View.OnClickListener() {
                        @Override
                        public void onClick(View p2) {
                            if(item.getCommand()==null){
                                showSnackbar("Under construction");
                            }else{
                                showMessage(item.getTitle(getActivity()));
                                new BackgroundThread(item.getCommand()).start();
                            }
                            builder.dismiss();

                        }
                    })
                    .setButton2Click(new View.OnClickListener(){

                        @Override
                        public void onClick(View p1)
                        {
                            // TODO: Implement this method
                            builder.dismiss();
                        }
                    });
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            card.setCardBackgroundColor(Color.WHITE);
                            textTitle.setTextColor(Color.GRAY);
                        }
                    });
                    builder.show();
                //mItems.get(position).getClick().onClick(view);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        mItems.add(new ClickItem(R.string.reboot_text, null, REBOOT_CMD, Effectstype.Shake));
        mItems.add(new ClickItem(R.string.sf_reboot_text, null, REBOOT_SOFT_REBOOT_CMD, Effectstype.Fadein));
        mItems.add(new ClickItem(R.string.safe_mode_text, null, null, Effectstype.Fall));
        mItems.add(new ClickItem(R.string.recovery_text, null, REBOOT_RECOVERY_CMD, Effectstype.Fliph));
        mItems.add(new ClickItem(R.string.bootloader_text, null, REBOOT_BOOTLOADER_CMD, Effectstype.RotateLeft));
        mItems.add(new ClickItem(R.string.shut_down_text, null, SHUTDOWN, Effectstype.Newspager));

        mAdapter.notifyDataSetChanged();

        return rootView;
    }

    private void showSnackbar(String message){
        ((MainActivity)getActivity()).showSnackbar(message);
    }

    private void setThreadPrio(int prio) {
        android.os.Process.setThreadPriority(prio);
    }

    private void showMessage(String action){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(getActivity(), "Starting...", "Running "+action);
        ringProgressDialog.setCancelable(false);
    }

    public class BackgroundThread extends Thread {
        private Object sCmd;

        private BackgroundThread(Object cmd) {
            this.sCmd = cmd;
        }

        @Override
        public void run() {
            super.run();
            setThreadPrio(BG_PRIO);

            if (sCmd == null)
                return;

            /**
             * Sending a system broadcast to notify apps and the system that we're going down
             * so that they write any outstanding data that might need to be flushed
             */
            Shell.SU.run(SHUTDOWN_BROADCAST);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (sCmd instanceof String)
                        Shell.SU.run((String) sCmd);
                    else if (sCmd instanceof String[])
                        Shell.SU.run((String[]) sCmd);
                }
            }, RUNNABLE_DELAY_MS);
        }
    }

    class CheckRoot extends AsyncTask<Void, Void, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ((MainActivity)getActivity()).showSnackbar("Checking root status", Snackbar.LENGTH_INDEFINITE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!aBoolean){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogCustom);
                builder.setTitle(R.string.app_name);
                builder.setCancelable(false);
                builder.setMessage("Root access not available. Application will exit");
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        ((MainActivity)getActivity()).finish();
                    }
                });
                builder.show();
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            //setThreadPrio(BG_PRIO);

            if (Shell.SU.available()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        showSnackbar("Root Available");
                    }
                });
                return true;
            }
            return false;
        }
    }
}
