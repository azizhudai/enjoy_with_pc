package com.mindfulness.pc_ticareti

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.stars

class RepairComputerActivity : AppCompatActivity() {

    private var alertDialog1: AlertDialog? = null
    private var values =
        arrayOf<CharSequence>("", "", "")

    /* private lateinit var ana_kart_id: CardView
     private lateinit var islemci_id: CardView
     private lateinit var ekran_karti_id: CardView
     private lateinit var power_id: CardView
     private lateinit var ram_id: CardView
     private lateinit var ssd_id: CardView*/

    private var computerPartList: ArrayList<ComputerPart> = ArrayList()

    private var computerPartListStatus: ArrayList<ComputerPart> = ArrayList()

    // private var priceList = ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair_computer)

        // Thread.sleep(10000)
        /*val tenSeconds = 10.seconds
        if(tenSeconds.equals(10000))
        {
            var repairComputer:ConstraintLayout = findViewById(R.id.repairComputer)
            repairComputer.setBackgroundColor(Color.parseColor("#121212"))

        }*/

        //Toast.makeText(this,"Sepete eklemek için resimlere tıklayınız")

        computerDataListBind()

    }

    fun cv_anakart(view: View) {
        values[0] = "Msi h310I (100 \$)"
        values[1] = "Asus Rog Strix H370 (150 \$)"
        values[2] = "Msi X299 (300 \$)"

        CreateAlertDialogWithRadioButtonGroup("Ana Kart Seçiniz", 1)
    }

    fun cv_islemci(view: View) {

        values[0] = "Amd Ryzen 5 (100 \$)"
        values[1] = "Amd Ryzen 7 (200 \$)"
        values[2] = "Amd Ryzen 9 (400 \$)"

        CreateAlertDialogWithRadioButtonGroup("İşlemci Seçiniz", 2)
    }

    fun cv_ekrankarti(view: View) {

        values[0] = "Nvidia GTX 1050 (200 \$)"
        values[1] = "Nvidia GTX 1660 (250 \$)"
        values[2] = "Nvidia RTX 2060 (300 \$)"
        CreateAlertDialogWithRadioButtonGroup("Ekran Kartı Seçiniz", 3)
    }

    fun cv_power(view: View) {

        values[0] = "530W (50 \$)"
        values[1] = "750W (65 \$)"
        values[2] = "900W (100 \$)"

        CreateAlertDialogWithRadioButtonGroup("Güç Kaynağı Seçiniz", 4)
    }

    fun cv_ram(view: View) {

        values[0] = "2x8GB 3200MHz (100 \$)"
        values[1] = "2x16GB 3200MHz (200 \$)"
        values[2] = "2x32GB 3200MHz (300 \$)"

        CreateAlertDialogWithRadioButtonGroup("Ram Seçiniz", 5)
    }

    fun cv_ssd(view: View) {

        values[0] = "Kingston SSD A400 480 GB (60 \$)"
        values[1] = "Kingston SSD A400 960 GB (100 \$)"
        values[2] = "Kingston SSD A400 1920 GB (200 \$)"

        CreateAlertDialogWithRadioButtonGroup("Ssd Disk Seçiniz", 6)
    }
    
    fun BtnEx(view: View) {

        OrderCreateAlertDialogWithRadioButtonGroup()
        /*  val builder = AlertDialog.Builder(this)
          with(builder) {
              Log.e("TAGGG", computerPartListStatus[0].title)
              setTitle("Icon and Button Color")
              setMessage("We have a message")
              setPositiveButton("OK", null)
              setNegativeButton("CANCEL", null)
              setNeutralButton("NEUTRAL", null)
              setPositiveButtonIcon(resources.getDrawable(android.R.drawable.ic_menu_call, theme))
              setIcon(resources.getDrawable(android.R.drawable.ic_dialog_alert, theme))
          }
          val alertDialog = builder.create()
          alertDialog.show()

          val button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
          with(button) {
              setBackgroundColor(Color.BLACK)
              setPadding(0, 0, 20, 0)
              setTextColor(Color.WHITE)
          }*/
    }

    fun CreateAlertDialogWithRadioButtonGroup(title: String, typeId: Int) {
        val builder =
            AlertDialog.Builder(this@RepairComputerActivity)
        builder.setTitle(title)
        builder.setSingleChoiceItems(values, -1,
            DialogInterface.OnClickListener { dialog, item ->

                var compPartGet: ComputerPart? = checkTitleAndPriceComputerPart(item, typeId)

                when (item) {
                    // ÖNCEKİ DEĞİRİ SİL... FUNCTION YAZILCAK..

                    0
                    -> {

                        computerPartListStatus.add(
                            ComputerPart(
                                0,
                                typeId,
                                compPartGet!!.title,
                                compPartGet!!.price
                            )
                        )
                        /* Toast.makeText(
                             this@RepairComputerActivity,
                             "First Item Clicked",
                             Toast.LENGTH_LONG
                         ).show()*/
                    }

                    1 -> {
                        computerPartListStatus.add(
                            ComputerPart(
                                1,
                                typeId,
                                compPartGet!!.title,
                                compPartGet!!.price
                            )
                        )
                    }
                    2 -> computerPartListStatus.add(
                        ComputerPart(
                            2,
                            typeId,
                            compPartGet!!.title,
                            compPartGet!!.price
                        )
                    )
                }
                alertDialog1?.dismiss()
            })
        alertDialog1 = builder.create()
        alertDialog1?.show()
    }

    override fun onStart() {
        super.onStart()
        stars.onStart()
    }

    override fun onStop() {
        stars.onStop()
        super.onStop()
    }

    fun checkTitleAndPriceComputerPart(partid: Int, typeid: Int): ComputerPart {

        //ilk olarAk önceden bu türden ürünü eklemişmiyiz bak var ise sil...
        for ((index, value) in computerPartListStatus.withIndex()) {
            if (value.type == typeid) {
                computerPartListStatus.removeAt(index)
                //ComputerPart(partid, typeid, items.title, items.price)
                break
            }
        }

        var computerPartGo = ComputerPart()
        for (items in computerPartList) {
            if (items.computerPartId == partid && items.type == typeid) {
                computerPartGo = ComputerPart(partid, typeid, items.title, items.price)
                break
            }
        }
        return computerPartGo
    }

    private fun computerDataListBind() {

        computerPartList.add(ComputerPart(0, 1, "Msi h310I (100 $)", 100))
        computerPartList.add(ComputerPart(1, 1, "Asus Rog Strix H370 (150 \$)", 150))
        computerPartList.add(ComputerPart(2, 1, "Msi h310I (100 \$)", 300))

        computerPartList.add(ComputerPart(0, 2, "Amd Ryzen 5 (100 \$)", 100))
        computerPartList.add(ComputerPart(1, 2, "Amd Ryzen 7 (200 \$)", 200))
        computerPartList.add(ComputerPart(2, 2, "Amd Ryzen 9 (400 \$)", 400))

        computerPartList.add(ComputerPart(0, 3, "Nvidia GTX 1050 (200 \$)", 200))
        computerPartList.add(ComputerPart(1, 3, "Nvidia GTX 1660 (250 \$)", 250))
        computerPartList.add(ComputerPart(2, 3, "Nvidia RTX 2060 (300 \$)", 300))

        computerPartList.add(ComputerPart(0, 4, "900W (100 \$)", 100))
        computerPartList.add(ComputerPart(1, 4, "750W (65 \$)", 65))
        computerPartList.add(ComputerPart(2, 4, "530W (50 \$)", 50))

        computerPartList.add(ComputerPart(0, 5, "2x8GB 3200MHz (100 \$)", 100))
        computerPartList.add(ComputerPart(1, 5, "2x16GB 3200MHz (200 \$)", 200))
        computerPartList.add(ComputerPart(2, 5, "2x32GB 3200MHz (300 \$)", 300))

        computerPartList.add(ComputerPart(0, 6, "Kingston SSD A400 480 GB (60 \$)", 60))
        computerPartList.add(ComputerPart(1, 6, "Kingston SSD A400 960 GB (100 \$)", 100))
        computerPartList.add(ComputerPart(2, 6, "Kingston SSD A400 1920 GB (200 \$)", 200))

    }

    // Method to show an alert dialog with multiple choice list items
    private fun OrderCreateAlertDialogWithRadioButtonGroup() {
        // Late initialize an alert dialog object
        lateinit var dialog: AlertDialog

        var priceTotal = 0
        var titleList: Array<String> = Array(computerPartListStatus.size) { "" }
        for ((index, value) in computerPartListStatus.withIndex()) {
           // Log.e("Tag${index}", value.title + value.price)
            priceTotal += value.price
            titleList[index] = value.title
        }

        // Initialize an array of colors
        // val arrayColors = arrayOf("RED", "GREEN", "YELLOW", "BLACK", "MAGENTA", "PINK")

        // Initialize a boolean array of checked items
        val arrayChecked = booleanArrayOf(true, true, true, true, true, true)

        //yardımcı ol butonu eklenebilir...

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(this)

        // Set a title for alert dialog
        builder.setTitle("Siparişteki Ürün Listesi ve Total Ücret ( $priceTotal\$ )")

        // Define multiple choice items for alert dialog
        val multiChoiceItems =
            builder.setMultiChoiceItems(titleList, arrayChecked) { dialog, which, isChecked ->
                // Update the clicked item checked status
                arrayChecked[which] = isChecked

                // Get the clicked item
                val title = titleList[which]

                // Display the clicked item text
                //    toast("$color clicked.")
            }

        if (priceTotal != 0) {
            builder.setNegativeButton("Ürün Bulunamadı!") { _,_ ->

            }
        } else {
            // Set the positive/yes button click listener
            builder.setPositiveButton("Satın Al!") { _, _ ->
                // Do something when click positive button
                /*   text_view.text = "Your preferred colors..... \n"
                   for (i in 0 until arrayColors.size) {
                       val checked = arrayChecked[i]
                       if (checked) {
                           text_view.text = "${text_view.text}  ${arrayColors[i]} \n"
                       }
                   }*/
            }
        }

        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }

}
