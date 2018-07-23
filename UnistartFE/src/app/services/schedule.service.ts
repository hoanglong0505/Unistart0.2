import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor() { }
  createWeek(week) {
week = [{'dateTitle': 'Thứ Hai', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}
]},
{'dateTitle': 'Thứ Ba', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}]},
{'dateTitle': 'Thứ Tư', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}]},
{'dateTitle': 'Thứ Năm', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}]},
{'dateTitle': 'Thứ Sáu', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}]},
{'dateTitle': 'Thứ Bảy', 'listLession': [{'no': 1, 'lessionName': '', 'note': ''},
{'no': 2, 'lessionName': '', 'note': ''},
{'no': 3, 'lessionName': '', 'note': ''},
{'no': 4, 'lessionName': '', 'note': ''},
{'no': 5, 'lessionName': '', 'note': ''},
{'no': 6, 'lessionName': '', 'note': ''},
{'no': 7, 'lessionName': '', 'note': ''},
{'no': 8, 'lessionName': '', 'note': ''},
{'no': 9, 'lessionName': '', 'note': ''},
{'no': 10, 'lessionName': '', 'note': ''}]},
];
return week;
  }
loadDay(days) {
  const list = [];
  days.forEach(element => {
   list.push({'date': element.itemName, 'id': element.id, 'starTime': '', 'endTime': ''});
});
console.log(list);
return list;
}

}
