export class Payroll {
  id: number;
  week: string;
  periodStart: Date;
  periodEnd: Date;

	constructor(week?: string, periodStart?: Date, periodEnd?: Date) {
		this.week = week;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
	}

}
