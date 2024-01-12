package presentation.Utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Modifier.ifTrue(value: Boolean,modifier:Modifier) :Modifier{
  return  this.run {
        if (value)
        {
            return this.then(modifier)
        }else{
            this
        }
    }
}