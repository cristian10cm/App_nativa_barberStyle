import com.example.app_native_store.data.appointmentsList
import com.example.app_native_store.types.AppointmentType

fun removeAppointment(appointment: AppointmentType) {
    appointmentsList.remove(appointment)
}