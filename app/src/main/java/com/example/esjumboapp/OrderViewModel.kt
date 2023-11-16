package com.example.esjumboapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.esjumboapp.Data.OrderUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

private const val HARGA_PER_CUP = 5000

class OrderViewModel : ViewModel() {
    private val _stateui = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateui.asStateFlow()

    fun setJumlah(jmlEsTeler:Int){
        _stateui.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsTeler,
                harga = hitungHarga(jumlah = jmlEsTeler)
            )
        }
    }

    fun setRasa(rasaPilihan: String) {
        _stateui.update { stateSaatIni ->
            stateSaatIni.copy(rasa = rasaPilihan) }
    }

    fun resetOrder(){
        _stateui.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateui.value.jumlah,
    ): String {
        val kalkulasHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasHarga)
    }

    fun setContact(listData: MutableList<String>){
        _stateui.value = OrderUIState(
            nama = listData[0],
            alamat = listData[1],
            noTlp = listData[2],
        )
    }
}
