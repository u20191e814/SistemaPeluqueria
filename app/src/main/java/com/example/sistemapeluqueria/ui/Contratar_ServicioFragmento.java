package com.example.sistemapeluqueria.ui;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemapeluqueria.InicioSesionActivity;
import com.example.sistemapeluqueria.MainActivity;
import com.example.sistemapeluqueria.R;
import com.example.sistemapeluqueria.RegistrarClienteActivity;
import com.example.sistemapeluqueria.controladores.webServicio;
import com.example.sistemapeluqueria.model.subcategoria;
import com.example.sistemapeluqueria.model.ubicacionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Contratar_ServicioFragmento extends Fragment {


    private String nombre ;
    private int fk_categoria;
    private int pk_personal;
    private Spinner cbosubcategoria;
    private List<subcategoria> listasubcategoria ;
    private ArrayAdapter<subcategoria>  adapterSubCategoria;
    private TextView txtPrecio ;
    private  TextView txtTotal;
    private TextView txtCantidad;
    private  TextView txtFecha ;
    private  int mYear;
    private int mMonth;
    private int  mDay;
    private TextView txtHora;
    private Button btnTomar;
    private ImageView img ;
    private Button btnContratar ;
    private TextView txtdireccion;
    private String mostrarEspecialista;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
            fk_categoria = getArguments().getInt("fk_categoria");
            pk_personal= getArguments().getInt("pk_personal");
            mostrarEspecialista= getArguments().getString("mostrarEspecialista");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View r = inflater.inflate(R.layout.fragment_contratar__servicio, container, false);
        TextView nombrePersonal = r.findViewById(R.id.txtNombrePersonalContratarServicio);
        txtPrecio = r.findViewById(R.id.txtprecioContratarServicio);
        txtTotal = r.findViewById(R.id.txtTotalContratarServicio);
        txtCantidad= r.findViewById(R.id.txtCantidadContratarServicio);
        txtFecha= r.findViewById(R.id.txtFechaContratarServicio);
        txtHora= r.findViewById(R.id.txtHoraContratarServicio);
        cbosubcategoria= r.findViewById(R.id.cboSubCategoria);
        btnTomar= r.findViewById(R.id.btnTomarFotoContratarServicio);
        img= r.findViewById(R.id.imgFotoContratarServicio);
        txtdireccion= r.findViewById(R.id.txtDireccionContratarServicio);
        btnContratar= r.findViewById(R.id.btnContratarServicio);
        listasubcategoria = new ArrayList<>();

        adapterSubCategoria = new ArrayAdapter<>(r.getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, listasubcategoria);
        cbosubcategoria.setAdapter(adapterSubCategoria);
        RequestQueue requestQueue= Volley.newRequestQueue(r.getContext());

        String url = webServicio.dominio_servicio+ "api/peluqueria/getsubCategoria?fk_categoria="+fk_categoria;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
        {

            @Override public void onResponse(String response)
            {
                try
                {
                    listasubcategoria.clear();
                    JSONObject OB = new JSONObject(response);
                    String estado= OB.getString("status");
                    if (!estado.equals("OK")) {
                        Toast toast = Toast.makeText(getContext(), OB.getString("statusMessage"), Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    JSONArray jsonArray = OB.getJSONArray("data");

                    String data = OB.getString("data");
                    for (int i = 0; i<jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        subcategoria reg =  new subcategoria(object.getInt("pk_subCategoria"), object.getString("nombre"), object.getInt("fk_categoria"), object.getDouble("precio"));
                        listasubcategoria.add(reg);

                    }

                    adapterSubCategoria.notifyDataSetChanged();

                }
                catch (JSONException e)
                {
                    Log.i("======> e", e.getMessage());
                }
            }
        }, new Response.ErrorListener()
        {
            @Override public void onErrorResponse(VolleyError error)
            {
                Log.i("====> e", error.toString());
            }
        } );

        requestQueue.add(stringRequest);

        cbosubcategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subcategoria sub=  (subcategoria) adapterView.getSelectedItem();
                txtPrecio.setText(String.valueOf(sub.getPrecio()) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtPrecio.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ActualizarTotal ();
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
});
        txtCantidad.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ActualizarTotal ();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
});
        txtFecha.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Calendar mcurrentDate = Calendar.getInstance();
                                             mYear = mcurrentDate.get(Calendar.YEAR);
                                             mMonth = mcurrentDate.get(Calendar.MONTH);
                                             mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);


                                            DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                                    Calendar myCalendar = Calendar.getInstance();
                                                    myCalendar.set(Calendar.YEAR, selectedyear);
                                                    myCalendar.set(Calendar.MONTH, selectedmonth);
                                                    myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                                                    String myFormat = "dd/MM/yyyy"; //Change as you need
                                                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                                                    txtFecha.setText(sdf.format(myCalendar.getTime()));

                                                    mDay = selectedday;
                                                    mMonth = selectedmonth;
                                                    mYear = selectedyear;
                                                }
                                            }, mYear, mMonth, mDay);
                                            //mDatePicker.setTitle("Select date");
                                            mDatePicker.show();
                                        }
                                    }

        );

        txtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                int hour = mcurrentDate.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentDate.get(Calendar.MINUTE);
                TimePickerDialog mDatePicker = new TimePickerDialog(getContext(), new TimePickerFragment(txtHora),hour, minute,false );
                mDatePicker.show();
            }
        });
        nombrePersonal.setText(nombre);

        btnTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                    Manifest.permission.CAMERA
            },100);
        }

        btnContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subcategoria sub= (subcategoria) cbosubcategoria.getSelectedItem();
                if (sub==null){
                    Toast.makeText(getContext(), "Se debe elegir una subcategoría", Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity lectura = (MainActivity)getActivity();
                if ( txtCantidad.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "La cantidad se encuentra vacia", Toast.LENGTH_SHORT).show();
                    return;
                }
                int cantidad = Integer.parseInt(txtCantidad.getText().toString());
                if (cantidad==0){
                    Toast.makeText(getContext(), "La cantidad no puede ser 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                double total = cantidad*sub.getPrecio();
                if (txtFecha.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Ingrese datos en la fecha", Toast.LENGTH_SHORT).show();
                    return;
                }
                String fecha = txtFecha.getText().toString();
                if (txtHora.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Ingrese datos en la hora", Toast.LENGTH_SHORT).show();
                    return;
                }
                String hora = txtHora.getText().toString();
                if (txtdireccion.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Ingrese datos en dirección", Toast.LENGTH_SHORT).show();
                    return;
                }
                String direccion = txtdireccion.getText().toString();
                Drawable dra= img.getDrawable();
                if (dra==null){
                    Toast.makeText(getContext(), "Debe tomar una imagen", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap bm=((BitmapDrawable)img.getDrawable()).getBitmap();
                if (bm ==null){
                    Toast.makeText(getContext(), "Debe tomar una imagen", Toast.LENGTH_SHORT).show();
                    return;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);


                String url = "http://3.145.140.134:9090/api/peluqueria/servicio";

                JSONObject jsonobject = new JSONObject();
                try {

                    jsonobject.put("id_cliente", lectura.id_cliente);
                    jsonobject.put("fk_categoria", fk_categoria);
                    jsonobject.put("fk_subcategoria", sub.getPk_subcategoria());
                    jsonobject.put("fk_personal", pk_personal);
                    jsonobject.put("precio", sub.getPrecio());
                    jsonobject.put("cantidad", cantidad);
                    jsonobject.put("total", total);
                    jsonobject.put("fecha", fecha);
                    jsonobject.put("hora", hora);
                    jsonobject.put("direccion", direccion);
                    jsonobject.put("nombrePersonal", nombre);
                    jsonobject.put("nombreServicio", mostrarEspecialista +" - "+ sub.getNombre() );
                    jsonobject.put("estado", "Pendiente" );
                    jsonobject.put("imgConstancia",encoded);

                    Log.i("Contratar servicio", jsonobject.toString());
                } catch (JSONException e) {
                    Log.i("errorjon======>", e.getMessage());
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, jsonobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Success Callback
                                Log.i("Contratar servicio", response.toString());

                                try {
                                    JSONObject obj = new JSONObject(response.toString());
                                    Integer data = obj.getInt("data");
                                    if (data == 0){
                                        Toast.makeText(getContext(), "No se pudo registrar", Toast.LENGTH_LONG).show();
                                    }
                                    if (data >0){
                                        Toast.makeText(getContext(), "Se contrató un servicio", Toast.LENGTH_LONG).show();
                                        NavController nav = Navigation.findNavController(view);
                                        nav.navigate(R.id.nav_servicioscontratados);
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                                Log.i("error======>", error.getMessage());
                            }
                        }
                );

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(jsonObjReq);
            }
        });
        return  r;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            Bitmap capture = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(capture);
        }
    }

    private void ActualizarTotal() {
        if ( !txtPrecio.getText().toString().isEmpty() && !txtCantidad.getText().toString().isEmpty()){
            double total = Double.parseDouble(txtPrecio.getText().toString()) * Double.parseDouble(txtCantidad.getText().toString());
            txtTotal.setText(String.valueOf(total));
        }
       else{
            txtTotal.setText(String.valueOf(0));
        }
    }
    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        TextView mResultText;

        public TimePickerFragment(TextView textView) {
            mResultText = textView;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time =  hourOfDay +":"+ minute;
            mResultText.setText(time);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}