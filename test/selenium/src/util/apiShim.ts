import CP from 'child_process'
import FS from 'fs'

import {envOpts} from 'test/selenium'

export function ShimApiTests(pattern: RegExp) {
    beforeAll(() => {
        const out = CP.execSync(`RDECK_URL=${envOpts.RUNDECK_URL} bash ./rundecklogin.sh - admin admin`, {cwd: '../api'})
    })

    let tests = FS.readdirSync('../api')

    tests = tests.filter(t => pattern.test(t) && t.endsWith('.sh'))

    tests.forEach(t => {
        it(t, () => {
            try {
                const out = CP.execSync(`RDECK_URL=${envOpts.RUNDECK_URL} bash ./${t} -`, {cwd: '../api'})
            } catch (e) {
                const ex = e as Error
                ex.message = `${e.stdout.toString()}\n${e.message}`
                throw e
            }
        })
    })
}