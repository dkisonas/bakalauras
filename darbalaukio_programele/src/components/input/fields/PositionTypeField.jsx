import React from "react";

function PositionTypeField({ formControl, setParent }) {
  const { register, getValues } = formControl;
  const handleChange = (e) => {
    setParent(e.target.value);
  };
  return (
    <div>
      <fieldset className="mt-4 mb-4">
        <legend className="sr-only">Notification method</legend>
        <div className="space-y-4 sm:flex sm:items-center sm:space-y-0 sm:space-x-10">
          <div key={"radio-item-1"} className="flex items-center">
            <input
              id="radio-item-1"
              name="radio-item-1"
              type="radio"
              value="Port"
              onChangeCapture={handleChange}
              defaultChecked={getValues("positionType") === "Port"}
              className="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300"
              {...register("positionType")}
            />
            <label
              htmlFor={"radio-item-1"}
              className="ml-3 block text-sm font-medium text-gray-700"
            >
              Port
            </label>
          </div>
          <div key={"radio-item-2"} className="flex items-center">
            <input
              id="radio-item-2"
              name="radio-item-2"
              type="radio"
              value="Coordinates"
              onChangeCapture={handleChange}
              defaultChecked={getValues("positionType") === "Coordinates"}
              className="focus:ring-indigo-500 h-4 w-4 text-indigo-600 border-gray-300"
              {...register("positionType")}
            />
            <label
              htmlFor={"radio-item-2"}
              className="ml-3 block text-sm font-medium text-gray-700"
            >
              Coordinates
            </label>
          </div>
        </div>
      </fieldset>
    </div>
  );
}

export default PositionTypeField;
