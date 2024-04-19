import {TypeModel} from "./type.model";

export interface MoveModel {
  id: number;
  name: string;
  icon: string;
  type: TypeModel;
}
