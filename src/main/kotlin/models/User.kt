package models

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject
import tornadofx.getValue
import tornadofx.setValue

open class User(name: String, username: String, email: String) : JsonModel {
  val nameProperty = SimpleStringProperty(name)
  var name: String? by nameProperty

  val usernameProperty = SimpleStringProperty(username)
  var username: String? by usernameProperty

  val emailProperty = SimpleStringProperty(email)
  var email: String? by emailProperty

  val statusProperty = SimpleObjectProperty<UserStatus>(UserStatus.OFFLINE)
  var status: UserStatus by statusProperty

  override fun updateModel(json: JsonObject) {
    with(json) {
      name = string("name")
      username = string("username")
      email = string("email")
      status = UserStatus.valueOf(string("status") ?: "OFFLINE")
    }
  }
}

open class UserModel : ItemViewModel<User>() {
  val name = bind(User::nameProperty)
  val username = bind(User::usernameProperty)
  val email = bind(User::emailProperty)
  val status = bind(User::statusProperty)
}
