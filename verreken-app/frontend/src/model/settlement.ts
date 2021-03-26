export interface Owe {
    id: number
    from: string
    to: string
    amount: number
}

export interface Date {
    day: string
    month: string
    year: string
}

export interface Settlement {
    id: number
    owes: Owe[]
    date: Date
}
