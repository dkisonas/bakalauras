import React from "react";
import GearDropdown from "../dropdowns/GearDropdown";
import Table from "./Table";
import TableDelButton from "./TableDelButton";
import DelBtnHeader from "./DelBtnHeader";
import {v4 as uuid} from "uuid";

function GearTable({ formControl, tableControl, registerName }) {
  const { fields, append, remove } = tableControl;

  const thead = (
    <thead className="bg-gray-50">
      <tr>
        <th scope="col" className="th-first">
          Name
        </th>
        <DelBtnHeader />
      </tr>
    </thead>
  );

  const tbody = (
    <tbody className="tbody">
      {fields.map((row, index) => (
        <tr key={row.id}>
          <td className="td-first">
            <GearDropdown
              formControl={formControl}
              registerName={registerName}
              attrName="gearTypeCode"
              index={index}
            />
          </td>

          <td className="table-btn-wrap">
            <TableDelButton remove={remove} index={index} />
          </td>
        </tr>
      ))}
    </tbody>
  );

  return (
    <Table
      name="Gear"
      description="Add your gear here"
      thead={thead}
      tbody={tbody}
      append={append}
      defaultObject={{
        identifier: uuid(),
        gearTypeCode: "",
      }}
    />
  );
}

export default GearTable;
