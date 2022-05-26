import React from "react";
import DateTimePicker from "react-datetime-picker";

function DatePickerField({label, value, setParent}) {
  return (
    <div>
      <label className="block text-sm font-medium text-gray-700">
        {label}
      </label>
      <DateTimePicker
        className="mt-1 w-auto"
        onChange={setParent}
        value={value}
        locale="lt-lt"
        required
      />
    </div>
  );
}

export default DatePickerField;
