import axios from 'axios'
import { Payment } from '../model/payment'

export default class HttpService {
  auth = {
    username: 'user',
    password: 'user'
  }

  async postPayments (payments: Payment[]): Promise<string> {
    return await axios.post('http://localhost:1245/api/payment', payments, {
      auth: this.auth
    })
  }

  async getHistory (): Promise<string> {
    return await axios.get('http://localhost:1245/api/history', {
      auth: this.auth
    })
  }
}
