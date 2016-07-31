package com.example.gerardo.gestordeclientes.ui.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardo.gestordeclientes.Funciones;
import com.example.gerardo.gestordeclientes.R;
import com.example.gerardo.gestordeclientes.adapter.ListaClientesAdapter;
import com.example.gerardo.gestordeclientes.model.Cliente;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.txt_titulo_main_fragment)
    TextView txtTitulo;
    @Bind(R.id.recycler_main_fragment)
    RecyclerView recyclerMain;
    RealmResults<Cliente> listaClientes;

    ListaClientesAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        listaClientes = Funciones.getClientes();
        if (listaClientes.size()==0){
            txtTitulo.setText("Sin clientes");
        }else{
            txtTitulo.setText("Clientes");
        }
        ArrayList<Cliente> cl = new ArrayList<>(listaClientes);
        adapter = new ListaClientesAdapter(getActivity(),cl);
        recyclerMain.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMain.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailClienteFragment detailClienteFragment = new DetailClienteFragment();
                Bundle args = new Bundle();
                args.putString("rut",adapter.getRutCliente(recyclerMain.getChildAdapterPosition(v)));
                detailClienteFragment.setArguments(args);
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
                        .replace(R.id.content_frame, detailClienteFragment)
                        .commit();
            }
        });

        initSwipe();

        return root;
    }

    private void initSwipe() {
        final Paint p = new Paint();
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    adapter.deleteCliente(position);
                }else{
                    ActualizarFragment actualizarFragment = new ActualizarFragment();
                    Bundle args = new Bundle();
                    args.putString("rut",adapter.getRutCliente(position));
                    actualizarFragment.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
                            .replace(R.id.content_frame, actualizarFragment)
                            .commit();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0){
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(),
                                dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,
                                (float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }else{
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerMain);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
