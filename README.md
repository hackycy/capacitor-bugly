# capacitor-bugly

![npm](https://img.shields.io/npm/v/capacitor-bugly)

📱 Bugly plugin for Capacitor

## Install

```bash
npm install capacitor-bugly
npx cap sync
```

## Setup

Edit your `capacitor.config.json` like below

``` json
// capacitor.config.json
{
	"appId": "**.***.**",
	"appName": "Name",
	"plugins": {
		"Bugly": {
			"androidAppId": "**",
      "iOSAppId": "**"
		}
	}
}
```

## API

<docgen-index>

* [`echo()`](#echo)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo()

```typescript
echo() => any
```

Test Bugly

**Returns:** <code>any</code>

--------------------

</docgen-api>
