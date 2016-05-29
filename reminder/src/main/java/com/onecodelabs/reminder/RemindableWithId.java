package com.onecodelabs.reminder;

public interface RemindableWithId extends Remindable {

    /*
    * This method should return a string that identifies this particular Remindable.
    * An example would be a detail view that display a product. You will want to implement
    * RemindableWithId this method should return the product id.
    * */
    String remindableId();
}
